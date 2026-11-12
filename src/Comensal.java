import java.util.Random;

/**
* Comensal
*/
public class Comensal {
	//Atributos básicos
	private String nombre;
	private float tiempoL;
	private float tiempoF;
	
	//Atributos para la generación de nombre aleatorio.
	private String Lista_nombres[]={"Diego","Romel","Aldo","Ivan","Brandon"};
	private String Lista_apellidos[]={"May","Guadarrama","Ontiveros","Crisostomo","Codova"};

	//Método constructor
	public Comensal(float tiempoL,float tiempoF){
		this.tiempoL=tiempoL;
		this.tiempoF=tiempoF;
		
		this.nombre=generar_nombre_aleatorio();
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
	/*
	public ver_menu();
	public solicitar_mesa();
	public solicitar_comida;
	public comer();
	public solicitar_cuenta();
	public calificar();
	*/
	//Método para generar nombre aleatorio.
	public String generar_nombre_aleatorio(){
		Random random=new Random();
		int indxN=random.nextInt(Lista_nombres.length);
		int indxA=random.nextInt(Lista_apellidos.length);
		return Lista_nombres[indxN]+" "+Lista_apellidos[indxA];
	}

}

