package ar.edu.unq.travel;

public class Reserva {
	//private Estado estadoInicial;
	//private Traslado metodoTraslado;
	//private MedioDePago medioDePago;
	private Estado estado;
	//private List<Observador> observadores;
	
	public Reserva() {
		this.estado = new Pendiente();
	}
	
	public Estado getEstado() {
		return this.estado;
	}
	
	public void cambiarAEstado(Estado estado) {
		this.estado = estado;
	}
	public void confirmar() {
		estado.confirmar(this);
	}
	public void enCheckin() {
		estado.enCheckin(this);
	}
	public void enCurso() {
		estado.enCurso(this);
	}
	public void finalizada() {
		estado.finalizada(this);
	}
	public void cancelada() {
		estado.cancelada(this);
	}
	
}
