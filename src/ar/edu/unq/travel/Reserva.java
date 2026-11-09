package ar.edu.unq.travel;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.Pendiente;
import ar.edu.unq.travel.notificaciones.Observer;
import ar.edu.unq.travel.plataformaTransporte.Direccion;
import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.traslados.Traslado;

public class Reserva {
	private Estado estado;

	private Traslado metodoTraslado;
	//private MedioDePago medioDePago;
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

	//-------

	public void agregarServicio(Servicio servicio){
		this.getEstado().agregarServicio(this,servicio);
	}

	public void quitarServicio(Servicio servicio){
		this.getEstado().quitarServicio(this,servicio);
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

	public boolean elCarritoContiene(Servicio servicio){
		return getCarrito().contiene(servicio);
	}

	private Carrito getCarrito(){
		return carrito;
	}

	//-------

	public void cambiarAEstado(Estado estadoNuevo) {
		this.estadoAnterior = estado;
		this.estado = estadoNuevo;
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

	public Estado getEstado() {
		return this.estado;
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

	public double costoDeServicioConTasas(){
		return this.carrito.costoDeServicio() + this.carrito.tasasAdministrativas();
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


//la reserva se conecte a un catalogo
//el catalogo tiene una lista de servicios 
// el carrito de la reserva tiene una lista de productos del catalogo
}

