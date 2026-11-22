/*
Esta clase tiene como objetivo crear de forma aleatoria la bebida de una lista de opciones de bebidas
y dependiendo de la bebida se establece el tiempo de preparación por ejemplo un cafe o un te tarda más que 
una coca ya que pues la coca y las demás bebidas ya estan hechas.
*/

import java.util.Random;

public class Bebida extends Alimento {
    private String nombre_comensal;
    private int ID_mesa;
    //Estableciendo las bebidas disponibles
    private String Lista_P[]={"Coca","Agua","Jarritos","Cafe","Te"};
    public static final String Estado[]={"En Proceso","Lista","Entregada"};
    private int Tiempo_preparacion;
    private float total;
    private String estadoactual;//Aqui se guarda el estado de preparación.
    private String bebidaElegida;

    // Nuevo constructor sin parámetros para usarlo en Alimento[]
    public Bebida() {
        super("", 0.0f, 0.0f);
        calcular_tiempo_de_preparacion(Lista_P);
    }

    //Método constructor de Bebida.
    public Bebida(float precio,Comensal comensal,Mesa mesa){
        super("",precio,0);
        this.nombre_comensal=comensal.getNombre();
        this.ID_mesa=mesa.getIDmesa();
        calcular_tiempo_de_preparacion(Lista_P);
    }


    //Métodos auxiliares o getters y setters.

    public String getBebida(){
        return this.bebidaElegida;
    }

    public int getTiempoD(){
        return this.Tiempo_preparacion;
    }

    //Acciones o métodos de Bebida
    public void calcular_tiempo_de_preparacion(String Lista_P[]){
        Random random=new Random();
        int indice=random.nextInt(Lista_P.length);
        this.bebidaElegida=Lista_P[indice];

        switch (this.bebidaElegida) {
            case "Coca":
                this.Tiempo_preparacion=1000;
                setPrecio(25.0f);
                break;
            case "Agua":
                this.Tiempo_preparacion=1000;
                setPrecio(20.0f);
                break;
            case "Jarritos":
                this.Tiempo_preparacion=1000;
                setPrecio(22.0f);
                break;
            case "Cafe":
                this.Tiempo_preparacion=3000;
                setPrecio(35.0f);
                break;
            case "Te":
                this.Tiempo_preparacion=3000;
                setPrecio(30.0f);
                break;
            default:
                this.Tiempo_preparacion=2000;
                setPrecio(25.0f);
                break;
        }
         setNombre(this.bebidaElegida);
        setTiempoDP(this.Tiempo_preparacion);

    }

    public void calcular_total(){
        this.total=getPrecio();
    }

    public void setEstado(){
        this.estadoactual=Estado[0];
        System.out.println("La bebida ["+getNombre()+"] está: "+this.estadoactual);
        try{
            Thread.sleep(this.Tiempo_preparacion);
        }catch(Exception e){
            System.out.println("Ocurrio un problema al preparar la bebida.");
        }

        this.estadoactual=Estado[1];
        System.out.println("La bebida ["+getNombre()+"] está: "+this.estadoactual);
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println("Error al entregar la bebida.");
        }

        this.estadoactual=Estado[2];
        System.out.println("La bebida ["+getNombre()+"] está: "+this.estadoactual);

    }
    
    public String getEstado(){
        return this.estadoactual;
    }


}
