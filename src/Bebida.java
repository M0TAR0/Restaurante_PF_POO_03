
import java.util.Random;

public class Bebida extends Alimento {
    private String nombre_comensal;
    private int ID_mesa;
    //Estableciendo las bebidas disponibles
    private String Lista_P[]={"Coca","Agua","Jarritos","Cafe","Te"};
    public static final String Estado[]={"En Proceso","Lista","Entregada"};
    private int Tiempo_preparacion;
    private float total;
    private String estadoactual;

    //Método constructor de Bebida.
    public Bebida(String nombre,float precio,float Tiempo_de_preparacion,Comensal comensal,Mesa mesa,String Lista_P[]){
        super(nombre,precio,Tiempo_de_preparacion);
        this.nombre_comensal=comensal.getNombre();
        this.ID_mesa=mesa.getIDmesa();
        calcular_tiempo_de_preparacion(Lista_P);
    }


    //Métodos auxiliares o getters y setters.

    



    //Acciones o métodos de Bebida
    public void calcular_tiempo_de_preparacion(String Lista_P[]){
        Random random=new Random();
        int beb=random.nextInt(5)+1;
        
        switch (beb) {
            case 0:
                this.Tiempo_preparacion=1000;
            case 1:
                this.Tiempo_preparacion=1000;
            case 2:
                this.Tiempo_preparacion=1000;
                break;
            case 3:
                this.Tiempo_preparacion=3000;
                break;
            case 4:
                this.Tiempo_preparacion=3000;
                break;
            default:
                System.out.println("Bebida no disponible.");
                break;
        }

    }
    public void calcular_total(){}
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
