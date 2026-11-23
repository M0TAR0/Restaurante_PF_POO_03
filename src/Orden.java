public class Orden {
  private Comensal comensal;
  private String nombre_comensal;
  private int ID_mesa;
  private Alimento[] alimentos;
  private String estadoActual;
  private static String Estado[] = {"En proceso", "Lista", "Entregada"};
  private int Tiempo_preparacion;
  private float total;

  // Método constructor
  public Orden(Comensal comensal, Mesa mesa, Alimento[] alimentos) {
    this.comensal = comensal;
    this.nombre_comensal = comensal.getNombre();
    this.ID_mesa = mesa.getIDmesa();
    this.alimentos = alimentos;
    this.estadoActual = Estado[0];


    // Demás comidas
    calcular_tiempo_de_preparacion();
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

  public int getTiempoDP() {
    return this.Tiempo_preparacion;
  }

  public void orden() {
   System.out.println("Orden para: [COMENSAL] " + nombre_comensal + " (Mesa " + ID_mesa + ")");
    for (Alimento a : alimentos) {
      System.out.println(a.getNombre() + "..." + a.getPrecio());
    }
  }

  public void calcular_tiempo_de_preparacion() {
   this.Tiempo_preparacion = 0;
    for (Alimento a : alimentos) {
      this.Tiempo_preparacion += (int) a.getTiempoDP();
    }
  } // Aqui se sumarian todos los Tiempos de los alimentos.

  public void calcular_total() {
   this.total = 0;
    for (Alimento a : alimentos) {
      this.total += a.getPrecio();
    }
  }// La suma de los precios

  public void setEstado() {
    this.estadoActual = Estado[2];
    for (Alimento a : alimentos) {
      if (a instanceof Bebida) {
        ((Bebida) a).setEstado();
      }
    }
  }
    // Colocar los setEstado de los demás alimentos.
  public String getEstado() {
    return this.estadoActual; // Aqui se tomaria el estado de todos los alimentos
  }
}
