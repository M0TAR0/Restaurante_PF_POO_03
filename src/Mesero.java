// CLASE MESERO
// Puntos principales sobre el funcionamiento de esta clase:
// - Mesero funciona como un thread, donde cada mesero actúa de forma independiente.
// - Las acciones principales se ven descritas en el método Run(). Este método se encuentra al final, después de todos los demás métodos.
// - Cada método interno de Mesero actúa a partir de un condicional en Run(), los cuáles se verán determinados a partir de los eventos
//   que vayan ocurriendo en el restaurante: Llegó un cliente, se quiere pedir una orden, una orden está lista, alguien quiere pagar...
//
// Las clases relacionadas directamente con esta son: Comensal, Mesa y Cocina

import java.util.Random;

public class Mesero extends Persona {

    //ATRIBUTOS
    Random random = new Random();
    int tiempo_de_traslado;
    public boolean mesero_libre = true;

    //MÉTODOS
    //1. El mesero toma una orden
    //2. El mesero lleva la orden a la cocina
    //3. Cuando la comida está lista se lleva la orden a la mesa
    //4. Cuando el cliente lo solicite el mesero cobra
    //5. El mesero genera un ticket de la orden

    public void moverse() { //Método empleado para simular la traslación de un mesero
        tiempo_de_traslado = random.nextInt(3000, 6000); //Tiempo de traslado entre 3 y 6 segundos

        try {
            sleep(tiempo_de_traslado);
        } catch (Exception e) {
            System.out.println("Error en traslado de mesero");
        }
    }

    // Se ocupa orden y cómo comensal va a pedir alimentos para continuar
    
    public void tomar_orden(String nombre_comensal) { //Crea una orden a partir de una lista de alimentos y bebidas que el comensal haya elegido
        this.moverse();

        // lista_de_alimentos = alimentos;
        // Orden orden = new Orden(nombre_comensal, lista de alimentos,);
        return orden;
    }
    
    public void llevar_a_cocina() {}

    public void entregar_orden() {}

    public void cobrar() {}

    public void generar_ticket() {}

    // MÉTODO RUN:
    // Método principal del hilo, que llevará la lógica de las acciones a realizar por cada mesero:
    // 1. Si el mesero está libre esperar a que haya una actividad por hacer
    // 2. Al encontrar una actividad, cambiar a ocupado y evaluar qué hacer para cada actividad
    // 3. Realizar la acción: Traslado + Ejecutar acción
    // 4. Cambiar a desocupado y repetir
    // Asumimos que las acciones se irán guardando en una lista de prioridad
    
    @Override
    public void run() {
        while(true) {
            if mesero_libre {
                //1. Checar si la lista de tareas no está vacía
                //2. Obtener qué acción realizar
                //3. Determinar qué hacer
            }
        }
        
    }
}

