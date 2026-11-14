package ar.edu.unq.travel;

import java.time.LocalDate;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.SolicitudPendiente;
import ar.edu.unq.travel.notificaciones.Publicador;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.plataformaTransporte.Direccion;
import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.traslados.Traslado;

public class Reserva {	
	
	private Estado estado;
	private Publicador publicador;
	private Traslado metodoTraslado;
	private MetodoDePago metodoDePago;
	private Carrito carrito;
	private Direccion direccionDestino;

	//Atributos necesarios para calcular cupos con fechas
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int cantPersonas;
	//#######################

	public Reserva(Traslado traslado, MetodoDePago metodoDePago, Traslado metodoTraslado, LocalDate inicio, LocalDate fin, int personas, Direccion destino) {
		this.estado = new SolicitudPendiente();
		this.carrito = new Carrito();
		this.metodoTraslado = traslado;
		this.metodoDePago = metodoDePago;
		this.metodoTraslado = metodoTraslado;
		this.metodoDePago = metodoDePago;
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.cantPersonas = personas;
		this.direccionDestino = destino;
	}

	//########## OTROS ##########

	public Direccion getDireccion() {
		return this.direccionDestino;
	}

	public int getCantPersonas() {
		return this.cantPersonas;
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
	
	public void cambiarAEstado(Estado estadoNuevo) {
		this.estado = estadoNuevo;
	}

	//########## CARRITO ##########

	private Carrito getCarrito() {
		return carrito;
	}
	
	public void agregarAlCarrito(Servicio servicio) {
		if (!servicio.hayDisponibilidad(this.getFechaInicio(), this.getFechaFin(), this.getCantPersonas())) {
			throw new IllegalStateException("No hay cupos disponibles para este servicio en las fechas seleccionadas");
		}
		getCarrito().addServicio(servicio);
		//this.getPublicador().actualizar();
	}

	public void eliminarDelCarrito(Servicio servicio) {
		this.getCarrito().removeServicio(servicio);
	}

	public void confirmarCarrito() {
		this.getCarrito().confirmar(getFechaInicio(), getFechaFin(), getCantPersonas());
	}

	public void cancelarCarrito() {
		this.getCarrito().cancelar(getFechaInicio(), getFechaFin(), getCantPersonas());
	}

	public double costoDeServicio() {
		return this.getCarrito().costoDeServicio();
	}

	public double tasasAdministrativas() {
		return this.getCarrito().tasasAdministrativas();
	}

	public double costoDeServicioConTasas() {
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
 