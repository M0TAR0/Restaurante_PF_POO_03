// Desarrollo principal del Restaurante:
//      - Creación de Comensales
//      - Creación de Meseros
//      - Implementación de cola de prioridad para acciones

public class mainRestaurante {

  public static void main(String[] args) {

    // Creación de clases
    for (int i = 0; i < 20; i++) {
      Thread comensal = new Comensal(10, 10);
      comensal.start();
    }

    for (int i = 0; i < 3; i++) {
      Thread mesero = new Mesero();
      mesero.start();
    }
  }
}
