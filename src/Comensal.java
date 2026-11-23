/** Comensal */
import java.util.Random;
  
public class Comensal extends Persona {
  // Atributos básicos
  private float tiempoL;
  private float tiempoF;
  private mainRestaurante restaurante;

  // Método constructor
  public Comensal(float tiempoL, float tiempoF, mainRestaurante restaurante) {
    this.restaurante = restaurante;
    this.tiempoL = tiempoL;
    this.tiempoF = tiempoF;
  }

  public float getTiempoL() {
    return this.tiempoL;
  }

  public float getTiempoF() {
    return this.tiempoF;
  }

  // Métodos o acciones del Comensal
  public void ver_menu() {}

  public void solicitar_mesa(Mesa mesa) {
    System.out.println("[COMENSAL] " + getNombre() + " Solicita mesa.");
    if (mesa.getEstado() == true) {
      System.out.println(
          "Se asigno la mesa: " + mesa.getIDmesa() + " Al comensal: [COMENSAL] " + getNombre());
      mesa.setEstado();
    } else {
      System.out.println("No hay mesas disponibles.");
    }
  }

  // El comensal elige sus alimentos y los deja como acción pendiente (prioridad 2) para un mesero
  public void solicitar_comida(Mesa mesa) {
     Random random = new Random();
    Alimento[] alimentos;

    if (random.nextBoolean()) {
      alimentos = new Alimento[] {new Bebida(), new Comida(), new Postre()};
    } else {
      alimentos = new Alimento[] {new Bebida(), new Comida()};
    }

    restaurante.agregar_accion(2, this, mesa, alimentos);
  }

  public void comer() {}

  public void solicitar_cuenta() {}

  public void calificar() {}

  @Override
  public void run() {

    // Falta: asignación real de mesa, esperar a que le sirvan, comer y encolar la acción 3 (cobrar)
    Mesa mesa = new Mesa(this);
    this.solicitar_comida(mesa);
  }
}
