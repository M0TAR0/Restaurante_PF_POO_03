import java.util.Random;

/** Comensal */
public class Comensal extends Persona {
  // Atributos básicos
  private mainRestaurante restaurante;
  private Orden ordenComensal;
  private boolean servido = false;
  private boolean pagado = false;
  private int tiempo_de_espera;
  private Alimento[] alimentos;
  private Mesa mesa;

  // Método constructor
  public Comensal(mainRestaurante restaurante) {
    this.restaurante = restaurante;
  }

  // Getters y Setters
  public synchronized Orden getOrden() {
    return this.ordenComensal;
  }

  public Alimento[] getAlimentos() {
    return this.alimentos;
  }

  public synchronized void setServido(boolean servido) {
    this.servido = servido;
  }

  public synchronized void setPagado(boolean pagado) {
    this.pagado = pagado;
  }

  public synchronized void setOrden(Orden orden) {
    this.ordenComensal = orden;
  }

  // Métodos o acciones del Comensal
  public void esperar() { // Llegada escalonada de los comensales a la fila
    Random random = new Random();
    tiempo_de_espera = random.nextInt(300, 600);
    System.out.println("[COMENSAL] " + this.getNombre() + " llega al restaurante...");
    try {
      sleep(tiempo_de_espera);
    } catch (Exception e) {
      System.out.println("Error en la espera del comensal");
    }
  }

  // Se bloquea hasta que haya una de las 7 mesas libres
  public void solicitar_mesa() throws InterruptedException {
    System.out.println("[COMENSAL] " + getNombre() + " solicita mesa.");
    this.mesa = restaurante.asignar_mesa();
    System.out.println("Se asignó la mesa " + mesa.getIDmesa() + " al comensal [COMENSAL] " + getNombre());
  }

  // El comensal elige sus alimentos y los deja como acción pendiente (prioridad 2) para un mesero
  public void solicitar_comida() {
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
    System.out.println("[COMENSAL] " + this.getNombre() + " está comiendo en la mesa " + mesa.getIDmesa());
    try {
      sleep(random.nextInt(1000, 2000)); // Comiendo: 10 a 20 s simulados
    } catch (Exception e) {
      System.out.println("Error mientras el comensal comía");
    }
  }

  public void solicitar_cuenta(Orden orden) {
    restaurante.agregar_accion(3, orden);
  }

  public synchronized boolean getServido() {
    return this.servido;
  }

  public synchronized boolean getPagado() {
    return this.pagado;
  }

  private void dormir(int ms) {
    try {
      sleep(ms);
    } catch (Exception e) {
      System.out.println("Hubo un error en Comensal");
    }
  }

  public void calificar() {
    Random random = new Random();
    int calificacion = random.nextInt(6); // 0 a 5 estrellas
    System.out.println("[COMENSAL] " + getNombre() + " califica con " + calificacion + " estrellas");
    restaurante.registrar_calificacion(calificacion);
  }

  @Override
  public void run() {
    this.esperar();

    try {
      this.solicitar_mesa();
    } catch (InterruptedException e) {
      return;
    }

    this.solicitar_comida();
    while (!getServido()) { // Espera a que el mesero le entregue la orden
      dormir(20);
    }

    this.comer();
    this.solicitar_cuenta(this.getOrden());
    while (!getPagado()) { // Primero paga...
      dormir(20);
    }

    this.calificar(); // ...y después califica
    System.out.println("[COMENSAL] " + getNombre() + " libera la mesa " + mesa.getIDmesa() + " y se va.");
    restaurante.liberar_mesa(mesa);
  }
}
