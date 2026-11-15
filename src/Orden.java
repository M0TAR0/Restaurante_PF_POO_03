public class Orden {
	private String nombre_comensal;
	private int ID_mesa;
	private Bebida bebidaO;
	private static String Estado[]={"En proceso","Lista","Entregada"};
	private int Tiempo_preparacion;
	private float total;
	private int contadorp=0;
	
	//Método constructor
	public Orden(Comensal comensal,Mesa mesa){
		this.nombre_comensal=comensal.getNombre();
		this.ID_mesa=mesa.getIDmesa();
		this.bebidaO=new Bebida(35.0f, comensal, mesa);
		//Demás comidas
	}
	
	public void orden(){
		System.out.println("Orden para: "+nombre_comensal+" (Mesa "+ID_mesa+")");
		System.out.println("Bebida: "+bebidaO.getBebida()+"..."+bebidaO.getPrecio());
		//Aqui irian las demás comidas
	}

	//Acciones que debe realizar la clase.
	public void calcular_tiempo_de_preparacion(){
		int Tiempo_preparacion=bebidaO.getTiempoD();// Aqui se sumarian todos los Tiempos de los alimentos.
	}
	public void calcular_total(){
		this.total=bebidaO.getPrecio();//La suma de los precios
	}
	public void setEstado(){
		bebidaO.setEstado();
		//Colocar los setEstado de los demás alimentos.
	}
	public String getEstado(){
		//Retornar estado
	}

}	

