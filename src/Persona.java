public class Persona extends Thread {
  private String nombre;
  private int edad;

  public Persona() { // Generamos nombre y edad aleatoriamente cada que se instancia el objeto
    this.nombre = this.generarNombre();
    this.edad = this.generarEdad();
  }

  public String generarNombre() {
    String nombre_generado = "";
    // Aleatorio
    // return nombre;
    return nombre_generado;
  }

  public int generarEdad() {
    int edad_generada = 0;
    // Aleatorio
    // return edad;
    return edad_generada;
  }
}
