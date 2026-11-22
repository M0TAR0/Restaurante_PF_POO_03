// Desarrollo principal del Restaurante:
//      - Creación de Comensales
//      - Creación de Meseros
//      - Implementación de cola de prioridad para acciones

import java.util.PriorityQueue;

public class mainRestaurante {

  final int TOTAL_COMENSALES = 20;
  int cobrados = 0;
  float ganancia_total = 0;

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
  public void agregar_accion(int prioridad, Comensal comensal, Mesa mesa, Alimento[] alimentos) {
    acciones.add(new Accion(prioridad, comensal, mesa, alimentos));
  }

  public void agregar_accion(int prioridad, Orden orden) {
    acciones.add(new Accion(prioridad, orden));
  }

  public void registrar_cobro(float monto) {
    ganancia_total += monto;
    cobrados++;
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

    for (int i = 0; i < TOTAL_COMENSALES; i++) {
      Thread comensal = new Comensal(10, 10, this);
      comensal.start();
    }

    for (int i = 0; i < 3; i++) {
      meseros[i].join(); // Espera a que los meseros terminen
    }

    System.out.println("=================================================");
    System.out.println("REPORTE FINAL DE JORNADA");
    System.out.println("=================================================");
    System.out.println("Total de comensales atendidos : " + cobrados);
    System.out.println("Mesas operativas : 7");
    System.out.println("Meseros en turno : 3");
    System.out.println("-------------------------------------------------");
    System.out.println("Ganancia Total Facturada : $" + ganancia_total + " MXN");
    System.out.println("=================================================");
  }

  public static void main(String[] args) throws InterruptedException {
    new mainRestaurante().iniciar();
  }
}
