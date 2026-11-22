// CLASE MESERO
// Puntos principales sobre el funcionamiento de esta clase:
// - Mesero funciona como un thread, donde cada mesero actúa de forma independiente.
// - Las acciones principales se ven descritas en el método Run(). Este método se encuentra al
// final, después de todos los demás métodos.
// - Cada método interno de Mesero actúa a partir de un condicional en Run(), los cuáles se verán
// determinados a partir de los eventos
//   que vayan ocurriendo en el restaurante: Llegó un cliente, se quiere pedir una orden, una orden
// está lista, alguien quiere pagar...
//
// Las clases relacionadas directamente con esta son: Comensal, Mesa y Cocina

import java.util.Random;

public class Mesero extends Persona {

  // ATRIBUTOS
  Random random = new Random();
  int tiempo_de_traslado;
  public boolean mesero_libre = true;
  Cocina cocina;
  mainRestaurante restaurante;

  public Mesero(Cocina cocina, mainRestaurante restaurante) {
    this.cocina = cocina;
    this.restaurante = restaurante;
  }

  // MÉTODOS
  // 1. El mesero toma una orden
  // 2. El mesero lleva la orden a la cocina
  // 3. Cuando la comida está lista se lleva la orden a la mesa
  // 4. Cuando el cliente lo solicite el mesero cobra
  // 5. El mesero genera un ticket de la orden

  public void moverse() { // Método empleado para simular la traslación de un mesero
    tiempo_de_traslado = random.nextInt(3000, 6000); // Tiempo de traslado entre 3 y 6 segundos
    try {
      sleep(tiempo_de_traslado);
    } catch (Exception e) {
      System.out.println("Error en traslado de mesero");
    }
  }

  // Toma los alimentos que el comensal quiere pedir y crea la orden
  public Orden tomar_orden(Comensal comensal, Mesa mesa, Alimento[] alimentos) {
    this.moverse();
    try {
      sleep(500); // Toma de orden: 5 s simulados
    } catch (Exception e) {
      System.out.println("Error al tomar orden");
    }
    System.out.println(
        "[MESERO] " + this.getNombre() + " tomó la orden de [COMENSAL] " + comensal.getNombre());
    // Pendiente: pasar la lista de alimentos a Orden cuando esté desarrollada
    return new Orden(comensal, mesa);
  }

  public void llevar_a_cocina(Orden orden) {
    this.moverse();
    cocina.preparar(orden.getIDmesa());
    // Cuando la cocina termina, se agenda la acción de servir
    restaurante.agregar_accion(1, orden);
  }

  public void entregar_orden(Orden orden) {
    this.moverse();
    System.out.println(
        "[MESERO] " + this.getNombre() + " entregó la orden en la mesa " + orden.getIDmesa());
  }

  public void cobrar(Orden orden) {
    this.moverse();
    try {
      sleep(200); // Cobro y ticket: 2 s simulados
    } catch (Exception e) {
      System.out.println("Error al cobrar");
    }
    this.generar_ticket(orden);
    restaurante.registrar_cobro(orden.getTotal());
  }

  public void generar_ticket(Orden orden) {
    System.out.println(
        "TICKET | "
            + orden.getNombreComensal()
            + " | Mesa "
            + orden.getIDmesa()
            + " | $"
            + orden.getTotal());
  }

  // MÉTODO RUN:
  // Método principal del hilo, que llevará la lógica de las acciones a realizar por cada mesero:
  // 1. Si el mesero está libre esperar a que haya una actividad por hacer
  // 2. Al encontrar una actividad, cambiar a ocupado y evaluar qué hacer para cada actividad
  // 3. Realizar la acción: Traslado + Ejecutar acción
  // 4. Cambiar a desocupado y repetir
  // Las acciones se guardan en una cola de prioridad (mainRestaurante):
  // 1 = servir comida, 2 = tomar orden y llevar a cocina, 3 = cobrar

  @Override
  public void run() {
    while (restaurante.cobrados < restaurante.TOTAL_COMENSALES) {
      mainRestaurante.Accion accion = restaurante.acciones.poll(); // Toma la de mayor prioridad
      if (accion == null) { // No hay acciones, espera un momento
        try {
          sleep(50);
        } catch (Exception e) {
          System.out.println("Error en la espera del mesero");
        }
        continue;
      }

      mesero_libre = false;

      switch (accion.prioridad) {
        case 1:
          entregar_orden(accion.orden);
          break;
        case 2:
          Orden orden = tomar_orden(accion.comensal, accion.mesa, accion.alimentos);
          llevar_a_cocina(orden);
          break;
        case 3:
          cobrar(accion.orden);
          break;
      }
      mesero_libre = true;
    }
  }
}
