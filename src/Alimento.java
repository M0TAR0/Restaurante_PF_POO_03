public class Alimento {
    private String nombre;
    private float precio;

    //Método constructor
    public Alimento(String nombre,float precio){
        setNombre(nombre);
        this.precio=precio;
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
    
    public void setPrecio(float precio){
        this.precio=precio;
    }
}
