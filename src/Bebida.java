public class Bebida extends Alimento {
    private String nombre_comensal;
    private int ID_mesa;
    private String Lista_P[];
    private String Estado[]={"En Proceso","Lista","Entregada"};
    private float Tiempo_preparacion;
    private float total;

    public Bebida(String nombre,float precio,float Tiempo_de_preparacion,int ID_mesa,float Tiempo_preparacion,float total,String Lista_P, String Estado){
        super(nombre,precio,Tiempo_de_preparacion);
        nombre_comensal=Comensal.getNombre();
    }

}
