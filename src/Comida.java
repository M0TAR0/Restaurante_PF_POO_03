import java.util.Random;

public class Comida extends Alimento {

    private static final String[] CATALOGO_COMIDAS = {
        "Chilaquiles Verdes",
        "Enchiladas Suizas",
        "Tacos al Pastor",
        "Milanesa de Pollo",
        "Pozole Rojo"
    };

    private static final float[] PRECIOS_COMIDAS = {
        85.0f,
        95.0f,
        75.0f,
        105.0f,
        90.0f
    };

    public Comida() {
        super("", 0.0f, 0.0f);
        Random random = new Random();
        int indice = random.nextInt(CATALOGO_COMIDAS.length);
        setNombre(CATALOGO_COMIDAS[indice]);
        setPrecio(PRECIOS_COMIDAS[indice]);
    }

    @Override
    public String toString() {
        return String.format("%s ($%.2f)", getNombre(), getPrecio());
    }
}
