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

  public synchronized void registrar_calificacion(int calificacion) {
    SumaCalificaciones += calificacion;
    totalCalificaciones++;
  }

  public synchronized double obtener_promedio() {
    if (totalCalificaciones == 0) {
        return 0;
    }
    double promedio = SumaCalificaciones / totalCalificaciones;
    return promedio;
  }

  // ===== AQUÍ EMPIEZA LA FUNCION MAIN ======

  public void iniciar()
      throws InterruptedException { // Para poder pasar la clase main a las demás clases (Priority
    // queue)

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
      comensales[i].join(); // Espera a que los comensales terminen (para confirmar que todos calificaron)
    }

    System.out.println("=================================================");
    System.out.println("REPORTE FINAL DE JORNADA");
    System.out.println("=================================================");
    System.out.println("Total de comensales atendidos : " + cobrados);
    System.out.println("Mesas operativas : 7");
    System.out.println("Meseros en turno : 3");
    System.out.println("-------------------------------------------------");
    System.out.println("Ganancia Total Facturada : $" + ganancia_total + " MXN");
    System.out.println("Calificación del restaurante  : " + String.format("%.2f", obtener_promedio()) + " / 5 estrellas");
    System.out.println("=================================================");
  }

  public static void main(String[] args) throws InterruptedException {
    new mainRestaurante().iniciar();
  }
}
