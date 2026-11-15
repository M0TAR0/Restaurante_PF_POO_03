import java.util.Random;

/**
* Comensal
*/
public class Comensal extends Persona{
	//Atributos básicos
	private String nombre;
	private float tiempoL;
	private float tiempoF;
	
	//Atributos para la generación de nombre aleatorio.
	/*Por si el nombre si se genera en Comensal 
	private String Lista_nombres[]={"Diego","Romel","Aldo","Ivan","Brandon"};
	private String Lista_apellidos[]={"May","Guadarrama","Ontiveros","Crisostomo","Codova"};
	*/

	//Método constructor
	public Comensal(float tiempoL,float tiempoF,Persona persona){
		this.tiempoL=tiempoL;
		this.tiempoF=tiempoF;
		
		this.nombre=persona.getNombre();
	}
	
	
	//Métodos setters y getters.
	public String getNombre(){
		return this.nombre;
	}

	public float getTiempoL(){
		return this.tiempoL;
	}
	

	public float getTiempoF(){
		return this.tiempoF;
	}


	//Métodos o acciones del Comensal
	public void ver_menu(){}
	public void solicitar_mesa(Mesa mesa){
		System.out.println("Comensal: "+getNombre()+" Solicita mesa.");
		if(mesa.getEstado()==true){
			System.out.println("Se asigno la mesa: "+mesa.getIDmesa()+" Al comensal: "+getNombre());
			mesa.setEstado();
		}
		else{
			System.out.println("No hay mesas disponibles.");
		}
	}
	public void solicitar_comida(Mesero mesero){
		mesero.
	}
	public void comer(){}
	public void solicitar_cuenta(){}
	public void calificar(){}
	
	//Método para generar nombre aleatorio. 
	/* Por si el nombre aleatorio si se genera en comensal
	public String generar_nombre_aleatorio(){
		Random random=new Random();
		int indxN=random.nextInt(Lista_nombres.length);
		int indxA=random.nextInt(Lista_apellidos.length);
		return Lista_nombres[indxN]+" "+Lista_apellidos[indxA];
	}
	*/
}

