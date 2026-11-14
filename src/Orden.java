public class Orden {
	private String nombre_comensal;
	private int ID_mesa;
	private String ListaP[];
	private static String Estado[]={"En proceso","Lista","Entregada"};
	private int Tiempo_preparacion;
	private float total;
	
	//Método constructor
	public Orden(Comensal comensal,Mesa mesa){
		this.nombre_comensal=comensal.getNombre();
		this.ID_mesa=mesa.getIDmesa();
	}
	
	//Fución para crear la orden.
	public void setListaP(){
		Bebida bebida=new Bebida(nombre_comensal, ID_mesa, Tiempo_preparacion, null, null, ListaP);

	}
	
	//Acciones que debe realizar la clase.
	public void calcular_tiempo_de_preparacion(){}
	public void calcular_total(){}
	public void setEstado(){}
	public void gerEstado(){}

}	

