// CLASE COCINA
// - Recurso compartido por los 3 meseros: solo se prepara una orden a la vez.
// - Por eso preparar() es synchronized (sección crítica de producción).

public class Cocina {

  // Prepara la orden de una mesa. Tarda 8 s simulados (800 ms reales)
  public synchronized void preparar(int id_mesa) {
    System.out.println("[COCINA] Preparando orden de la mesa " + id_mesa);
    try {
      Thread.sleep(800);
    } catch (Exception e) {
      System.out.println("Error en la preparación de la cocina");
    }
    System.out.println("[COCINA] Orden lista para la mesa " + id_mesa);
  }
}
