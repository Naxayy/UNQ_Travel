package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.SolicitudPendiente;
import ar.edu.unq.travel.notificaciones.Observador;
import ar.edu.unq.travel.notificaciones.Publicador;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.traslados.Direccion;
import ar.edu.unq.travel.traslados.Traslado;

public class Reserva extends Publicador {	
	
	private Estado estado;
	private Traslado metodoTraslado;
	private MetodoDePago metodoDePago;
	private Carrito carrito;
	private Direccion direccionDestino;
	private int cantidadDePasajeros;
	private Estado estadoAnterior; 

	//Atributos necesarios para calcular cupos con fechas
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	//#######################

	public Reserva(Traslado traslado, MetodoDePago metodoDePago, LocalDate inicio, LocalDate fin, int personas, Direccion destino, List<Observador> observadores) {
		super(observadores);
		this.estado = new SolicitudPendiente();
		this.carrito = new Carrito();
		this.metodoTraslado = traslado;
		this.metodoDePago = metodoDePago;
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.direccionDestino = destino;
	}


	public void cambiarCarrito(Carrito carritoNuevo){
		this.carrito = carritoNuevo;
	}

	public Direccion getDireccion() {
		return this.direccionDestino;
	}


	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}

	//########## ESTADOS ##########

	public void agregarServicio(Servicio servicio) {
		this.getEstado().agregarServicio(this, servicio);
	}

	public void quitarServicio(Servicio servicio) {
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

	public Estado getEstadoAnterior(){
		return this.estadoAnterior;
	}

	public void cambiarAEstado(Estado estadoNuevo) {
		this.estadoAnterior = estado;
		this.estado = estadoNuevo;
		this.notificarAObservadores(this.getEstado());
	}

	//########## CARRITO ##########

	public Carrito getCarrito() {
		return carrito;
	}
	
	public void agregarAlCarrito(Servicio servicio) {
		this.carrito.addServicio(servicio, this.fechaInicio, this.fechaFin, this.cantidadDePasajeros);
	}

	public void eliminarDelCarrito(Servicio servicio) {
		this.carrito.removeServicio(servicio);
	}

	public void confirmarCarrito() {
		this.carrito.confirmar(getFechaInicio(), getFechaFin(), getCantidadPasajeros());
	}

	public void cancelarCarrito() {
		this.carrito.cancelar(getFechaInicio(), getFechaFin(), getCantidadPasajeros());
	}

	public double costoDeServicio() {
		return this.carrito.costoDeServicio();
	}

	private double tasasAdministrativas() {
		return this.carrito.tasasAdministrativas();
	}

	public double costoDeServicioConTasas() {
		return this.costoDeServicio() + this.tasasAdministrativas();
	}

	public double costoDeServiciosNoDisfrutados() {
		//falta esta logica
		return 0;
	}


	//###### REPORTES ######
	public List<Servicio> getServicios() {
		return this.carrito.getListaDelCarrito();
	}
	public int getCantidadPasajeros(){
		return this.cantidadDePasajeros; 
	}

	public Direccion direccionDestino(){
		return this.direccionDestino;
	}

	public void cambiarDireccionDestino(Direccion direccionNueva){
		this.direccionDestino = direccionNueva;
	}

}
 