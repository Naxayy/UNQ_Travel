package ar.edu.unq.travel;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.SolicitudPendiente;
import ar.edu.unq.travel.notificaciones.Publicador;
import ar.edu.unq.travel.plataformaTransporte.Direccion;
import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.traslados.Traslado;
import ar.edu.unq.travel.pagos.*;

public class Reserva {	
	
	private Estado estado;
	private Publicador publicador;
	private Traslado metodoTraslado;
	private MetodoDePago metodoDePago;
	private Carrito carrito;
	private Direccion direccionDestino;
	private int cantidadDePasajeros;

	public Reserva(Traslado traslado, MetodoDePago metodoDePago) {
		this.estado = new SolicitudPendiente();
		this.carrito = new Carrito();
		this.metodoTraslado = traslado;
		this.metodoDePago = metodoDePago;
		this.cantidadDePasajeros = 1;
	}

	public Reserva(Traslado traslado, MetodoDePago metodoDePago, Carrito carrito) {
		this.estado = new SolicitudPendiente();
		this.carrito = carrito;
		this.metodoTraslado = traslado;
		this.metodoDePago = metodoDePago;
	}

	public void agregarServicio(Servicio servicio){
		this.getEstado().agregarServicio(this, servicio);
	}

	public void quitarServicio(Servicio servicio){
		this.getEstado().quitarServicio(this, servicio);
	}

	public void confirmar() {
		this.getEstado().confirmar(this);
	}
	public void enCheckin() {
		this.getEstado().enCheckin(this);
	}
	public void enCurso() {
		this.getEstado().enCurso(this);
	}
	public void finalizar() {
		this.getEstado().finalizada(this);
	}
	public void cancelar() {
		this.getEstado().cancelada(this);
	}

	public Estado getEstado() {
		return this.estado;
	}

	public Carrito getCarrito(){
		return carrito;
	}

	public void cambiarAEstado(Estado estadoNuevo) {
		this.estado = estadoNuevo;
	}
	
	public void agregarAlCarrito(Servicio servicio){
		this.getCarrito().addServicio(servicio);
		//this.getPublicador().actualizar();
	}

	public void eliminarDelCarrito(Servicio servicio){
		this.getCarrito().removeServicio(servicio);
	}

	public void confirmarCarrito(){
		this.getCarrito().confirmar();
	}

	public void cancelarCarrito(){
		this.getCarrito().cancelar();
	}

	public double costoDeServicio(){
		return this.getCarrito().costoDeServicio() ;
	}

	public double tasasAdministrativas(){
		return this.getCarrito().tasasAdministrativas();
	}

	public double costoDeServicioConTasas(){
		return this.getCarrito().costoDeServicio() + this.carrito.tasasAdministrativas();
	}

	public int pasajeros(){
		return cantidadDePasajeros; 
	}

	public Direccion direccionDestino(){
		return this.direccionDestino;
	}

	public void cambiarDireccionDestino(Direccion direccionNueva){
		this.direccionDestino = direccionNueva;
	}

	public void cambiarLaCantidadDePasajeros(int pasajeros){
		this.cantidadDePasajeros = pasajeros;
	}
}
 