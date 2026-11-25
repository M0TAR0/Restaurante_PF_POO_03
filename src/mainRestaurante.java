// Desarrollo principal del Restaurante:
//      - Creación de Comensales
//      - Creación de Meseros
//      - Implementación de cola de prioridad para acciones

import java.util.PriorityQueue;

public class mainRestaurante {

  final int TOTAL_COMENSALES = 20;
  int cobrados = 0;
  float ganancia_total = 0;
  int SumaCalificaciones = 0;
  int totalCalificaciones = 0;

  // ===== PRIORITY QUEUE =====

  // Acción pendiente en el restaurante. Prioridad: 1 = servir, 2 = pedir orden, 3 = cobrar
  class Accion implements Comparable<Accion> {
    int prioridad;
    Orden orden; // La orden ya trae al comensal y la mesa (acciones 1 y 3)
    Comensal comensal; // Datos del pedido que aún no es orden (acción 2)
    Mesa mesa;
    Alimento[] alimentos;

    Accion(int prioridad, Orden orden) {
      this.prioridad = prioridad;
      this.orden = orden;
    }

    Accion(int prioridad, Comensal comensal, Mesa mesa, Alimento[] alimentos) {
      this.prioridad = prioridad;
      this.comensal = comensal;
      this.mesa = mesa;
      this.alimentos = alimentos;
    }

    @Override
    public int compareTo(Accion otra) {
      return this.prioridad - otra.prioridad;
    }
  }

  PriorityQueue<Accion> acciones = new PriorityQueue<>();

  // El mesero crea la Orden a partir de los alimentos
  public synchronized void agregar_accion(
      int prioridad, Comensal comensal, Mesa mesa, Alimento[] alimentos) {
    acciones.add(new Accion(prioridad, comensal, mesa, alimentos));
  }

  public synchronized void agregar_accion(int prioridad, Orden orden) {
    acciones.add(new Accion(prioridad, orden));
  }

  // Saca la acción de mayor prioridad (null si no hay)
  public synchronized Accion siguiente_accion() {
    return acciones.poll();
  }

  public synchronized void registrar_cobro(float monto) {
    ganancia_total += monto;
    cobrados++;
  }

  public synchronized int getCobrados() {
    return cobrados;
  }

  public synchronized void registrar_calificacion(int calificacion) {
    SumaCalificaciones += calificacion;
    totalCalificaciones++;
  }

  public synchronized double obtener_promedio() {
    if (totalCalificaciones == 0) {
      return 0;
    }
    return (double) SumaCalificaciones / totalCalificaciones;
  }

  // ===== MESAS (7 simultáneas) =====
  final int TOTAL_MESAS = 7;
  final Mesa[] mesas = new Mesa[TOTAL_MESAS];
  int mesasLibres = TOTAL_MESAS;

  // Bloquea al comensal hasta que exista una mesa libre y se la asigna
  public synchronized Mesa asignar_mesa() throws InterruptedException {
    while (mesasLibres == 0) {
      wait(); // Espera a que alguien libere una mesa
    }
    for (Mesa m : mesas) {
      if (m.getEstado()) {
        m.setEstado(); // Pasa a ocupada
        mesasLibres--;
        return m;
      }
    }
    return null;
  }

  public synchronized void liberar_mesa(Mesa mesa) {
    mesa.setEstado(); // Pasa a libre
    mesasLibres++;
    notifyAll(); // Avisa a los comensales que esperan
  }

  private void imprimir_bienvenida() {
    System.out.println("=================================================");
    System.out.println("  Bienvenido al restaurante Los Pooderosos");
    System.out.println("=================================================");
    System.out.println("Mesas disponibles : " + TOTAL_MESAS);
    System.out.println("Meseros en turno  : 3");
    System.out.println("Comensales en fila: " + TOTAL_COMENSALES);
    System.out.println("-------------------- MENÚ ---------------------");
    System.out.println("Bebidas:");
    for (int i = 0; i < Bebida.CATALOGO_BEBIDAS.length; i++) {
      System.out.println(
          "  - " + Bebida.CATALOGO_BEBIDAS[i] + " ... $" + Bebida.PRECIOS_BEBIDAS[i]);
    }
    System.out.println("Platos fuertes:");
    for (int i = 0; i < Comida.CATALOGO_COMIDAS.length; i++) {
      System.out.println(
          "  - " + Comida.CATALOGO_COMIDAS[i] + " ... $" + Comida.PRECIOS_COMIDAS[i]);
    }
    System.out.println("Postres:");
    for (int i = 0; i < Postre.CATALOGO_POSTRES.length; i++) {
      System.out.println(
          "  - " + Postre.CATALOGO_POSTRES[i] + " ... $" + Postre.PRECIOS_POSTRES[i]);
    }
    System.out.println("=================================================\n");
  }

  // ===== AQUÍ EMPIEZA LA FUNCION MAIN ======

  public void iniciar()
      throws InterruptedException { // Para poder pasar la clase main a las demás clases (Priority
    // queue)

    imprimir_bienvenida();
    Thread.sleep(2000); // Pausa para alcanzar a leer la bienvenida

    for (int i = 0; i < TOTAL_MESAS; i++) {
      mesas[i] = new Mesa(i + 1);
    }

    Cocina cocina = new Cocina();

    // Creación de clases
    Mesero[] meseros = new Mesero[3];
    for (int i = 0; i < 3; i++) {
      meseros[i] = new Mesero(cocina, this);
      meseros[i].start();
    }

    Comensal[] comensales = new Comensal[TOTAL_COMENSALES];
    for (int i = 0; i < TOTAL_COMENSALES; i++) {
      comensales[i] = new Comensal(this);
      comensales[i].start();
    }

    for (int i = 0; i < 3; i++) {
      meseros[i].join(); // Espera a que los meseros terminen
    }
    for (int i = 0; i < TOTAL_COMENSALES; i++) {
      comensales[i]
          .join(); // Espera a que los comensales terminen (para confirmar que todos calificaron)
    }

    System.out.println("=================================================");
    System.out.println("REPORTE FINAL DE JORNADA");
    System.out.println("=================================================");
    System.out.println("Total de comensales atendidos : " + cobrados);
    System.out.println("Mesas operativas : " + TOTAL_MESAS);
    System.out.println("Meseros en turno : 3");
    System.out.println("-------------------------------------------------");
    System.out.println("Ganancia Total Facturada : $" + ganancia_total + " MXN");
    System.out.println(
        "Calificación del restaurante  : "
            + String.format("%.2f", obtener_promedio())
            + " / 5 estrellas");
    System.out.println("=================================================");
  }

  public static void main(String[] args) throws InterruptedException {
    new mainRestaurante().iniciar();
  }
}
