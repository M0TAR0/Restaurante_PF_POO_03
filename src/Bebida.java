import java.util.Random;

public class Bebida extends Alimento {

    public static final String[] CATALOGO_BEBIDAS = {
        "Coca",
        "Agua",
        "Jarritos",
        "Cafe",
        "Te"
    };

    public static final float[] PRECIOS_BEBIDAS = {
        25.0f,
        20.0f,
        22.0f,
        35.0f,
        30.0f
    };

    public Bebida() {
        super("", 0.0f);
        Random random = new Random();
        int indice = random.nextInt(CATALOGO_BEBIDAS.length);
        setNombre(CATALOGO_BEBIDAS[indice]);
        setPrecio(PRECIOS_BEBIDAS[indice]);
    }
}
