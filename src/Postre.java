import java.util.Random;

public class Postre extends Alimento {

    private static final String[] CATALOGO_POSTRES = {
        "Flan Napolitano",
        "Pastel de Chocolate",
        "Pay de Limón",
        "Helado de Vainilla",
        "Arroz con Leche"
    };

    private static final float[] PRECIOS_POSTRES = {
        45.0f,
        60.0f,
        50.0f,
        40.0f,
        35.0f
    };

    public Postre() {
        super("", 0.0f, 0.0f);
        Random random = new Random();
        int indice = random.nextInt(CATALOGO_POSTRES.length);
        setNombre(CATALOGO_POSTRES[indice]);
        setPrecio(PRECIOS_POSTRES[indice]);
    }

    @Override
    public String toString() {
        return String.format("%s ($%.2f)", getNombre(), getPrecio());
    }
}
