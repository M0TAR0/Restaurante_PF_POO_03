import java.util.Random;

public class Persona extends Thread {
  private String nombre;
  private int edad;

  public Persona() { // Generamos nombre y edad aleatoriamente cada que se instancia el objeto
    this.nombre = this.generarNombre();
    this.edad = this.generarEdad();
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public int getEdad() {
    return this.edad;
  }

  public String generarNombre() {
    String nombre_generado = "";

    final String[] Lista_nombres = {"Diego", "Romel", "Aldo", "Ivan", "Brandon"};
    final String[] Lista_apellidos = {"May", "Guadarrama", "Ontiveros", "Crisostomo", "Codova"};

    Random random = new Random();
    int indxN = random.nextInt(Lista_nombres.length);
    int indxA = random.nextInt(Lista_apellidos.length);
    return Lista_nombres[indxN] + " " + Lista_apellidos[indxA];
  }

  public int generarEdad() {
    int edad_generada = 0;
    Random random = new Random();
    edad_generada = random.nextInt(20, 30);

    return edad_generada;
  }
}
