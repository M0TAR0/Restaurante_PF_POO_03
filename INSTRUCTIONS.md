# Proyecto Final — Programación Orientada a Objetos

**Universidad Nacional Autónoma de México · Facultad de Ingeniería**
Semestre 2026 · Práctica Integradora de Simulación y Concurrencia

---

## 1. Explicación del escenario y flujo operativo

El propósito es modelar computacionalmente el ciclo de atención de un restaurante mediante el paradigma de Programación Orientada a Objetos y la coordinación de procesos concurrentes con hilos. Se evaluará el control de acceso a recursos físicos limitados y la integridad transaccional del servicio.

### Diagrama de flujo

```
[20 Comensales en Fila] → (Compiten por 1 de las 7 Mesas) → [En Mesa]
  → (Toma orden: Cualquier Mesero libre / 5 s)
  → [Comanda a Cocina (con ID de Mesa)]
  → [Cocina: 8 s (synchronized)]
  → [Comiendo: 10 a 20 s]
  ← (Entrega: Cualquier Mesero libre, lleva la comida a la Mesa ID)
  → [Generación y Cobro: Cualquier Mesero libre / 2 s]
  → [Calificación Comensal: 0.0 a 5.0]
  → [Mesa Liberada para Siguiente Cliente]
```

### Dinámica del ciclo de servicio

1. **Llegada y Espera:** Arriban 20 comensales buscando asignación dentro de las 7 mesas disponibles en salón.
2. **Atención y Comanda:** Cualquier mesero que se encuentre desocupado toma la orden (5 s). La orden queda asociada formalmente al identificador de la mesa de origen.
3. **Cocción Centralizada:** La orden entra a la cocina, donde se procesa bajo un método estrictamente sincronizado (8 s).
4. **Entrega Desacoplada:** Una vez lista la comida, cualquier mesero libre la traslada desde cocina hasta la mesa correspondiente. El comensal consume sus alimentos (10 a 20 s).
5. **Liquidación y Liberación:** Cualquier mesero disponible cobra el ticket (2 s). El cliente emite una calificación (0.0 a 5.0), libera la mesa y abandona el sistema.

---

## 2. Reglas del proyecto y especificaciones técnicas

### A. Recursos y restricciones físicas

- **Comensales:** 20 instancias que transitarán por el ciclo de atención.
- **Mesas:** Recurso compartido con capacidad máxima de 7 unidades simultáneas, cada una con un identificador unívoco.
- **Pool de Meseros Desacoplado:** 3 meseros activos en turno. Atienden solicitudes conforme quedan libres (tomar orden, servir comida o cobrar). El mesero que entrega la comida no necesariamente es quien tomó la comanda, pero debe garantizarse la entrega en la mesa correcta.
- **Cocina Sincronizada:** La preparación de alimentos debe implementarse obligatoriamente mediante un método `synchronized` para resguardar la sección crítica de producción.

### B. Menú comercial

El catálogo de venta debe contar con **3 categorías fijas** (Bebidas, Platos Fuertes y Postres), con un **mínimo de 5 opciones por categoría** acompañadas de su respectivo precio. Cada orden debe incluir una selección válida de estos productos.

### C. Escala temporal de simulación

Con el fin de ejecutar las pruebas de manera ágil sin alterar las proporciones de tiempo, se adopta la escala: **1 segundo simulado = 100 milisegundos reales (ms)**.

| Actividad                                   | Tiempo simulado | Tiempo real      |
| ------------------------------------------- | --------------- | ---------------- |
| Toma de orden (Mesero disponible)           | 5 segundos      | 500 ms           |
| Preparación en Cocina (Método sincronizado) | 8 segundos      | 800 ms           |
| Consumo de alimentos (Comensal) (aleatorio) | 10 a 20 s       | 1,000 a 2,000 ms |
| Generación y cobro de ticket                | 2 segundos      | 200 ms           |

### D. Reto de ingeniería y concurrencia

- **Manejo de hilos y exclusión mutua:** Diseñar la estructura para que los 3 meseros atiendan dinámicamente las distintas tareas sin incurrir en condiciones de carrera, inanición (_starvation_) ni interbloqueos (_deadlocks_).
- **Integridad transaccional:** Implementar acumuladores seguros para hilos (_thread-safe_) para registrar la ganancia monetaria y las evaluaciones sin pérdida de datos.
- **Métricas de satisfacción:** La calificación del comensal (0.0 a 5.0) debe modelarse y calcularse de manera consistente con la calidad y fluidez del servicio recibido.

---

## 3. Entregable: formato del reporte de salida

Al completarse la atención de los 20 comensales y quedar el salón vacío, la aplicación debe imprimir en consola el resumen de cierre de jornada:
