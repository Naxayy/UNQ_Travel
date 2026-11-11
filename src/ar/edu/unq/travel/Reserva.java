package ar.edu.unq.travel;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.SolicitudPendiente;
import ar.edu.unq.travel.notificaciones.Publicador;
import ar.edu.unq.travel.plataformaTransporte.Direccion;
import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.traslados.Traslado;

public class Reserva {	
	
	private Estado estado;
	private Publicador publicador;
	private Traslado metodoTraslado;
	//private MedioDePago medioDePago;
	private Carrito carrito;
	private Direccion direccionDestino;

	public Reserva(Traslado traslado) {
		this.estado = new SolicitudPendiente(this);
		this.carrito = new Carrito();
		this.metodoTraslado = traslado;
		//preguntar :p
	}

	//-------

	public void agregarServicio(Servicio servicio){
		this.getEstado().agregarServicio(servicio);
	}

	public void quitarServicio(Servicio servicio){
		this.getEstado().quitarServicio(servicio);
	}

	public void confirmar() {
		this.getEstado().confirmar();
	}
	public void enCheckin() {
		this.getEstado().enCheckin();
	}
	public void enCurso() {
		this.getEstado().enCurso();
	}
	public void finalizar() {
		this.getEstado().finalizada();
	}
	public void cancelar() {
		this.getEstado().cancelada();
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
	*/

	public Direccion direccionDestino(){
		return this.direccionDestino;
	}
}

