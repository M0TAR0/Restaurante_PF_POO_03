import java.util.Random;

public class Persona extends Thread {
  private String nombre;

  public Persona() { // Generamos el nombre aleatoriamente cada que se instancia el objeto
    this.nombre = this.generarNombre();
  }

  public String getNombre() {
    return this.nombre;
  }

  public String generarNombre() {
    String nombre_generado = "";

    final String[] Lista_nombres = {"Diego", "Romel", "Aldo", "Ivan", "Brandon"};
    final String[] Lista_apellidos = {"May", "Guadarrama", "Ontiveros", "Crisostomo", "Cordova"};

    Random random = new Random();
    int indxN = random.nextInt(Lista_nombres.length);
    int indxA = random.nextInt(Lista_apellidos.length);
    return Lista_nombres[indxN] + " " + Lista_apellidos[indxA];
  }
}
