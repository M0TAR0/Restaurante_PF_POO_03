# PLANEACIÓN

Este documento tiene como finalidad desglosar todos los recursos y elementos a utilizar y desarrollar durante la elaboración del proyecto. Se describirán los procesos a ejecutar, las clases a implementar y especificaciones adicionales a considerar por el equipo.

## CLASES

Esta sección cubre las clases a emplear para la elaboración del proyecto. Cada una deberá incluir una descripción de su funcionalidad, sus atributos y métodos.

### **Personas**

#### Comensal

_A t r i b u t o s_

- Nombre
- Tiempo de llegada
- Tiempo de frustracion (Lo que tardan en querer irse si no los atienden)

_M é t o d o s_

- ver_menu()
- solicitar_mesa()
- solicitar_comida()
- comer()
- solicitar_cuenta()
- calificar()

#### Mesero

_A t r i b u t o s_

- Nombre
- Tiempo_de_espera (Lo que tarda en atender a un comensal)

_M é t o d o s_

- tomar_orden()
- llevar_a_cocina()
- entregar_orden()
- cobrar()
- generar_ticket()

### **Productos**

#### Alimento

_A t r i b u t o s_

- Nombre
- Precio
- Tiempo_de_preparacion

##### Comida

##### Bebida

##### Postre

#### Orden

_A t r i b u t o s_

- Nombre_comensal
- ID_MESA
- Lista_productos []
- Estado (En proceso, lista, entregada) //Para controlar cuando entregar ordenes o cuentas. Privada
- Tiempo_preparacion
- total

_M é t o d o s_

- calcular_tiempo_de_preparacion()
- calcular_total()
- set_estado()
- get_estado()

### **Apartados**

#### Cocina

_A t r i b u t o s_

- Lista_ordenes []
- Tiempo_de_finalizacion (Lo que tarda en preparar las ordenes pendientes)

_M é t o d o s_

- recibir_orden()
- preparar_orden()

#### Mesa

_A t r i b u t o s_

- ID_MESA (número de mesa)
- Nombre_comensal
- Estado ¿?

### Extra

#### Adicionales - Clase para manejar información adicional de aleatoriedad

_A t r i b u t o s_

- Lista_nombres [] //nombres aleatorios
- Lista_apellidos [] //apellidos aleatorios

_M é t o d o s_

- generar_nombre_aleatorio()

## HILOS

En esta sección se describirán los procesos a realizar durante el programa, los cuáles serán ejecutada de manera paralela por medio de hilos.

### 20 Hilos - COMENSALES

Cada hilo representará a un comensal, el cuál realizará una de estas acciones:

- LLegar y solicitar una mesa
- - Si hay va a la mesa, si no empieza a esperar.
- Ver el menú
- Pedir de comer
- Comer
- Solicitar cuenta
- Pagar (Tarjeta, efectivo)
- Dejar una reseña
- Irse

### 3 Hilos - MESEROS

Cada hilo representará a un mesero, los cuáles podrán realizar una de estas acciones (si están disponibles):

- Tomar orden
- Llevar orden a cocina
- LLevar orden a mesa
- Cobrar

## IDEAS ADICIONALES

En esta sección mencionaremos ideas adicionales a implementar para el programa, las cuales se podrán ir añadiendo conforme la funcinoalidad principal del programa sea completada.

**Lista de Ideas**

- Hacer todos los tiempos aleatorios
- Que los meseros puedan limpiar mesas tambien
