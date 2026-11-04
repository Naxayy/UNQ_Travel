package ar.edu.unq.travel;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
	//private Estado estadoInicial;
	//private Traslado metodoTraslado;
	//private MedioDePago medioDePago;
	private Estado estado = new Pendiente();
	private List<Observer> observers = new ArrayList<Observer>();
	
	public Reserva() {
		//La reserva tiene que resivir por parametro para ser creada el metodoTraslado y el medioDePago
		this.estado = new Pendiente();
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
	public void agregarObserver(Observer observer) {
		this.observers.add(observer);
	}
	public void eliminarObserver(Observer observer) {
		this.observers.remove(observer);
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
	public void finalizar() {
		estado.finalizada(this);
	}
	public void cancelar() {
		estado.cancelada(this);
	}

	public void agregarServicio(Servicio servicio){
		estado.agregarServicio(this);
	} 
	
}
