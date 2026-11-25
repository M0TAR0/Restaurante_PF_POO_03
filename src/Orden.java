public class Orden {
  private Comensal comensal;
  private String nombre_comensal;
  private int ID_mesa;
  private Alimento[] alimentos;
  private float total;

  // Método constructor
  public Orden(Comensal comensal, Mesa mesa, Alimento[] alimentos) {
    this.comensal = comensal;
    this.nombre_comensal = comensal.getNombre();
    this.ID_mesa = mesa.getIDmesa();
    this.alimentos = alimentos;
    calcular_total();
  }

  // Getters
  public String getNombreComensal() {
    return this.nombre_comensal;
  }

  public Comensal getComensal() {
    return this.comensal;
  }

  public int getIDmesa() {
    return this.ID_mesa;
  }

  public float getTotal() {
    return this.total;
  }

  public void calcular_total() {
    this.total = 0;
    for (Alimento a : alimentos) {
      this.total += a.getPrecio();
    }
  } // La suma de los precios
}
