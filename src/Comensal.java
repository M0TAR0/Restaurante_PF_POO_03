import java.util.Random;

/** Comensal */
public class Comensal extends Persona {
  // Atributos básicos
  private float tiempoL;
  private float tiempoF;
  private mainRestaurante restaurante;
  private Orden ordenComensal;
  private boolean servido = false; // El mesero lo cambia al entregar la orden

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

  public Orden getOrden() {
    return this.ordenComensal;
  }

  public void setServido(boolean servido) {
    this.servido = servido;
  }

  public void setOrden(Orden orden) {
    this.ordenComensal = orden;
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

  public void comer() {
    Random random = new Random();
    try {
      sleep(random.nextInt(1000, 2000)); // Comiendo: 10 a 20 s simulados
    } catch (Exception e) {
      System.out.println("Error mientras el comensal comía");
    }
  }

  public void solicitar_cuenta(Orden orden) {
    restaurante.agregar_accion(3, orden);
  }

  public void calificar() {}

  @Override
  public void run() {

    // Falta: asignación real de mesa
    Mesa mesa = new Mesa(this);
    this.solicitar_comida(mesa);
    while (!servido) { // Espera a que el mesero le entregue la orden
      try {
        sleep(50);
      } catch (Exception e) {
        System.out.println("Hubo un error en Comensal");
      }
    }
    this.comer();
    this.solicitar_cuenta(this.getOrden());
  }
}
