package ar.edu.unq.travel;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.Pendiente;
import ar.edu.unq.travel.notificaciones.Observer;
import ar.edu.unq.travel.servicios.Servicio;

public class Reserva {
	private Traslado metodoTraslado;
	//private MedioDePago medioDePago;
	private Estado estado;
	private Estado estadoAnterior;
	private List<Observer> observers = new ArrayList<Observer>();
	private Carrito carrito;
	private Direccion direccionDestino;
	//private Usuario usuario;

	public Reserva(Traslado traslado) {
		this.estado = new Pendiente();
		this.carrito = new Carrito();
		this.metodoTraslado = traslado;
		//preguntar :p

	}

	//HAY QUE MODELAR PASAJEROS Y DIRECCION

	//-------
	public Estado getEstadoAnterior(){
		return this.estadoAnterior;
	}

	public List<Servicio> getServicios(){
		return this.carrito.getServicios();
	}

	public double costoDeServicio(){
		return this.carrito.costoDeServicio() ;
	}

	public double tasasAdministrativas(){
		return this.carrito.tasasAdministrativas();
	}

	public int pasajeros(){
		return 0; //COMO CALCULO LOS PUTOS PASAJEROS
	}

	public Direccion direccionDestino(){
		return this.direccionDestino;
	}

	//-------
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

	//-------
	public Estado getEstado() {
		return this.estado;
	}
	public void cambiarAEstado(Estado estadoNuevo) {
		this.estadoAnterior = estado;
		this.estado = estadoNuevo;
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

	//-------
	public void agregarServicio(Servicio servicio){
		this.estado.agregarServicio(this,servicio);
	}

	public void quitarServicio(Servicio servicio){
		this.estado.quitarServicio(this,servicio);
	}

	public void agregarAlCarrito(Servicio servicio){
		this.carrito.addServicio(servicio);
	}

	public void eliminarDelCarrito(Servicio servicio){
		this.carrito.removeServicio(servicio);
	}

	public void confirmarCarrito(){
		this.carrito.confirmar();
	}

	public void cancelarCarrito(){
		this.carrito.cancelar();
	}

	//---------

	



//la reserva se conecte a un catalogo
//el catalogo tiene una lista de servicios 
// el carrito de la reserva tiene una lista de productos del catalogo
}

