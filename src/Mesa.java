/*
Este método funcionara de tal forma que generara el ID de la mesa y con su metodo setEstado se podra cambiar el estado que tenia por el otro,
es decir que si la mesa al incio estara libre por lo que su Estado=true por lo que si se asigna un Comenal a dicha mesa se mandaria a llamar el 
método setEstado y su estado cambiara de true a false.
*/

import java.util.Random;

public class Mesa {
	private int ID_MESA;
	private String nombre_comensal;
	private boolean Estado;

	//Método constructor de Mesa.
	public Mesa(Comensal comensal){
		setIDmesa();
		this.nombre_comensal=comensal.getNombre();
		this.Estado=true; //Estado en true lo tomare como, false: no esta libre y true: como esta libre,
	}
		
	public int getIDmesa(){
		return this.ID_MESA;
	}

	public void setIDmesa(){
		Random ran=new Random();
		this.ID_MESA=ran.nextInt(7)+1;
	}
	
	public boolean getEstado(){
		return this.Estado;
	}
	
	//Este método funcionara como un switch cada que se mande a llamar se cambiara su estado (libre/ocupado)
	public void setEstado(){
		if(this.Estado==true){
			this.Estado=false;
		}
		else if(this.Estado==false){
			this.Estado=true;
		}
	}

}
