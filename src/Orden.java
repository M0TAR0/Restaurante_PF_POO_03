public class Orden {
  private String nombre_comensal;
  private int ID_mesa;
  private Bebida bebidaO;
  private static String Estado[] = {"En proceso", "Lista", "Entregada"};
  private int Tiempo_preparacion;
  private float total;

  // Método constructor
  public Orden(Comensal comensal, Mesa mesa) {
    this.nombre_comensal = comensal.getNombre();
    this.ID_mesa = mesa.getIDmesa();
    this.bebidaO = new Bebida(35.0f, comensal, mesa);

    // Demás comidas
    calcular_tiempo_de_preparacion();
    calcular_total();
  }

  // Getters
  public String getNombreComensal() {
    return this.nombre_comensal;
  }

  public int getIDmesa() {
    return this.ID_mesa;
  }

  public float getTotal() {
    return this.total;
  }

  public int getTiempoDP() {
    return this.Tiempo_preparacion;
  }

  public void orden() {
    System.out.println("Orden para: [COMENSAL] " + nombre_comensal + " (Mesa " + ID_mesa + ")");
    System.out.println("Bebida: " + bebidaO.getBebida() + "..." + bebidaO.getPrecio());
    // Aqui irian las demás comidas
  }

  public void calcular_tiempo_de_preparacion() {
    this.Tiempo_preparacion =
        bebidaO.getTiempoD(); // Aqui se sumarian todos los Tiempos de los alimentos.
  }

  public void calcular_total() {
    this.total = bebidaO.getPrecio(); // La suma de los precios
  }

  public void setEstado() {
    bebidaO.setEstado();
    // Colocar los setEstado de los demás alimentos.
  }

  public String getEstado() {
    return bebidaO.getEstado(); // Aqui se tomaria el estado de todos los alimentos
  }
}
