package ar.edu.unq.travel;

import java.util.List;

import ar.edu.unq.travel.ciclo.*;
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

	public Reserva(Traslado traslado, MetodoDePago metodoDePago) {
		this.estado = new SolicitudPendiente();
		this.carrito = new Carrito();
		this.metodoTraslado = traslado;
		this.metodoDePago = metodoDePago;
		//preguntar :p
	}

	//-------

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

	//-------

	public Estado getEstado() {
		return this.estado;
	}

	private Carrito getCarrito(){
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

	/* Esto no va aca
	public int pasajeros(){
		return 0; 
	}

	public Direccion direccionDestino(){
		return this.direccionDestino;
	}
	*/
}
 