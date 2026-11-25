/*
Mesa del restaurante. Cada una tiene un ID unívoco (1 a 7).
Estado: true = libre, false = ocupada. El control de acceso lo hace mainRestaurante
(métodos synchronized con wait/notifyAll), por lo que nunca hay más de 7 mesas ocupadas.
*/

public class Mesa {
	private final int ID_MESA;
	private boolean Estado;

	public Mesa(int id) {
		this.ID_MESA = id;
		this.Estado = true;
	}

	public int getIDmesa() {
		return this.ID_MESA;
	}

	public synchronized boolean getEstado() {
		return this.Estado;
	}

	// Alterna el estado (libre/ocupada)
	public synchronized void setEstado() {
		this.Estado = !this.Estado;
	}
}
