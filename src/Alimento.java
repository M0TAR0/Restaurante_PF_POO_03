public class Alimento {
    private String nombre;
    private float precio;
    private float Tiempo_de_preparacion;

    //Método constructor
    public Alimento(String nombre,float precio,float Tiempo_de_preparacion){
        setNombre(nombre);
        this.precio=precio;
        this.Tiempo_de_preparacion=Tiempo_de_preparacion;
    }


    //Métodos getters y setters.
    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public float getPrecio(){
        return this.precio;
    }

    public float getTiempoDP(){
        return this.Tiempo_de_preparacion;
    }

}
