# PLANEACIÓN

Este documento tiene como finalidad desglosar todos los recursos y elementos a utilizar y desarrollar durante la elaboración del proyecto. Se describen los procesos a ejecutar, las clases implementadas y especificaciones adicionales a considerar por el equipo.

## CLASES

Esta sección cubre las clases empleadas en el proyecto, con su funcionalidad, atributos y métodos principales.

### **Personas**

#### Persona

Clase padre de Comensal y Mesero. Extiende `Thread` y asigna un nombre aleatorio.

_A t r i b u t o s_

- nombre

_M é t o d o s_

- getNombre()
- generarNombre()

#### Comensal

Cliente del restaurante. Recorre todo el ciclo de atención.

_A t r i b u t o s_

- restaurante
- ordenComensal
- servido
- pagado
- tiempo_de_espera (Lo que tarda en llegar)
- alimentos []
- mesa

_M é t o d o s_

- esperar()
- solicitar_mesa()
- solicitar_comida()
- comer()
- solicitar_cuenta()
- calificar()
- run()

#### Mesero

Atiende las acciones pendientes según su prioridad.

_A t r i b u t o s_

- cocina
- restaurante

_M é t o d o s_

- tomar_orden()
- llevar_a_cocina()
- entregar_orden()
- cobrar()
- generar_ticket()
- run()

### **Productos**

#### Alimento

Clase padre de los productos del menú.

_A t r i b u t o s_

- nombre
- precio

##### Comida

##### Bebida

##### Postre

Cada una elige al azar un producto de su catálogo (5 opciones con su precio).

#### Orden

Reúne al comensal, la mesa y los alimentos pedidos.

_A t r i b u t o s_

- comensal
- nombre_comensal
- ID_mesa
- alimentos []
- total

_M é t o d o s_

- calcular_total()

### **Apartados**

#### mainRestaurante

Clase principal. Crea las mesas, la cocina, los meseros y los comensales, e imprime el reporte final.

_A t r i b u t o s_

- TOTAL_COMENSALES
- TOTAL_MESAS
- mesas []
- mesasLibres
- acciones (cola de prioridad)
- cobrados
- ganancia_total
- SumaCalificaciones
- totalCalificaciones

_M é t o d o s_

- agregar_accion()
- siguiente_accion()
- asignar_mesa()
- liberar_mesa()
- registrar_cobro()
- getCobrados()
- registrar_calificacion()
- obtener_promedio()
- imprimir_bienvenida()
- iniciar()
- main()

#### Accion (clase interna de mainRestaurante)

Tarea pendiente para un mesero. Se ordena por prioridad.

_A t r i b u t o s_

- prioridad
- orden
- comensal
- mesa
- alimentos []

_M é t o d o s_

- compareTo()

#### Cocina

Recurso compartido: prepara una orden a la vez.

_M é t o d o s_

- preparar() (synchronized)

#### Mesa

_A t r i b u t o s_

- ID_MESA (número de mesa)
- Estado (libre u ocupada)

_M é t o d o s_

- getIDmesa()
- getEstado()
- setEstado()

## HILOS

En esta sección se describen los procesos del programa, los cuales se ejecutan de manera paralela por medio de hilos.

### 20 Hilos - COMENSALES

Cada hilo representa a un comensal, el cual realiza estas acciones en orden:

- Llegar y solicitar una mesa
  - Si hay, va a la mesa; si no, espera a que se libere una.
- Pedir de comer (bebida, plato fuerte y, opcionalmente, postre)
- Esperar su orden
- Comer
- Solicitar la cuenta
- Pagar
- Calificar (número entero de 0 a 5 al azar)
- Liberar la mesa e irse

### 3 Hilos - MESEROS

Cada hilo representa a un mesero, el cual toma de la cola la acción de mayor prioridad:

1. Llevar la orden a la mesa
2. Tomar la orden y llevarla a la cocina (permanece ahí hasta que está lista)
3. Cobrar y generar el ticket

## TIEMPOS

| Actividad              | Tiempo real      |
| ---------------------- | ---------------- |
| Llegada del comensal   | 300 a 600 ms     |
| Toma de orden          | 500 ms           |
| Preparación en cocina  | 800 ms           |
| Consumo de alimentos   | 1,000 a 2,000 ms |
| Cobro y ticket         | 200 ms           |

## IDEAS ADICIONALES

Ideas que quedaron fuera de esta versión y podrían añadirse después.

**Lista de Ideas**

- Hacer todos los tiempos aleatorios
- Que los meseros puedan limpiar mesas también
- Tiempo de frustración (lo que tarda un comensal en irse si no lo atienden)
- Elegir forma de pago (tarjeta o efectivo)
