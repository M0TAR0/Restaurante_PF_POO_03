import java.util.Random;

/** Comensal */
public class Comensal extends Persona {
  // Atributos básicos
  private mainRestaurante restaurante;
  private Orden ordenComensal;
  private boolean servido = false;
  private int tiempo_de_espera;
  private Alimento[] alimentos;

  // Método constructor
  public Comensal(mainRestaurante restaurante) {
    this.restaurante = restaurante;

  }

  //Getters y Setters
  public Orden getOrden() {
    return this.ordenComensal;
  }

  public Alimento[] getAlimentos(){
    return this.alimentos;
  }

  public void setServido(boolean servido) {
    this.servido = servido;
  }

  public void setOrden(Orden orden) {
    this.ordenComensal = orden;
  }

  // Métodos o acciones del Comensal
  public void esperar() { // Método empleado para simular el tiempo de espera de un comensal
    Random random = new Random();
    tiempo_de_espera = random.nextInt(300, 600); // Tiempo de espera: 3 a 6 s
    try {
      sleep(tiempo_de_espera);
      System.out.println("[COMENSAL] " + this.getNombre() + " está esperando para entrar...");
    } catch (Exception e) {
      System.out.println("Error en la espera del comensal");
    }
  }

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

    if (random.nextBoolean()) {
      this.alimentos = new Alimento[] {new Bebida(), new Comida(), new Postre()};
    } else {
      this.alimentos = new Alimento[] {new Bebida(), new Comida()};
    }

    restaurante.agregar_accion(2, this, mesa, this.alimentos);
  }

  public void comer() {
    Random random = new Random();
    try {
      sleep(random.nextInt(1000, 2000)); // Comiendo: 10 a 20 s simulados
      System.out.println("[COMENSAL] " + this.getNombre() + " está comiendo!");
    } catch (Exception e) {
      System.out.println("Error mientras el comensal comía");
    }
  }

  public void solicitar_cuenta(Orden orden) {
    restaurante.agregar_accion(3, orden);
  }

  public void calificar() {
    Random random = new Random();
    int calificacion = random.nextInt(6);
    System.out.println("[COMENSAL] " + getNombre() + " califica con " + calificacion + " estrellas");
    restaurante.registrar_calificacion(calificacion);
  }

  @Override
  public void run() {

    // Llegada y espera: el comensal espera un tiempo antes de pasar a su mesa
    this.esperar();

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
    this.calificar();
    this.solicitar_cuenta(this.getOrden());
  }
}