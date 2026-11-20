/** Comensal */
public class Comensal extends Persona {
  // Atributos básicos
  private float tiempoL;
  private float tiempoF;

  // Método constructor
  public Comensal(float tiempoL, float tiempoF) {
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
    System.out.println("Comensal: " + getNombre() + " Solicita mesa.");
    if (mesa.getEstado() == true) {
      System.out.println("Se asigno la mesa: " + mesa.getIDmesa() + " Al comensal: " + getNombre());
      mesa.setEstado();
    } else {
      System.out.println("No hay mesas disponibles.");
    }
  }

  public void solicitar_comida(Mesero mesero) {
    // mesero.solicitar(comida);
  }

  public void comer() {}

  public void solicitar_cuenta() {}

  public void calificar() {}

  @Override
  public void run() {
    System.out.println("Soy " + this.getNombre() + ", mi edad es " + this.getEdad());
  }
}
