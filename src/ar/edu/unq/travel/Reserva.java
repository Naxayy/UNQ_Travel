package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.ArrayList;
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
	private Direccion direccionDestino;
	private int cantidadDePasajeros;
	private Estado estadoAnterior; 

	private PeriodoDeTiempo periodoDeTiempo;
	//Atributos necesarios para calcular cupos con fechas
	//private LocalDate fechaInicio;
	//private LocalDate fechaFin;

	//private Carrito carrito;
	private List<Servicio> lista;

	//#######################

	public Reserva(Traslado traslado, MetodoDePago metodoDePago, PeriodoDeTiempo periodoDeTiempo, int personas, Direccion destino, List<Observador> observadores) {
		super(observadores);
		this.estado = new SolicitudPendiente();
		this.metodoTraslado = traslado;
		this.metodoDePago = metodoDePago;
		this.periodoDeTiempo = periodoDeTiempo;
		this.direccionDestino = destino;
		this.lista = new ArrayList<>();
		this.cantidadDePasajeros = personas;
	}

	public Direccion getDireccion() {
		return this.direccionDestino;
	}


	/*public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}*/

	public void cambiarPeriodoDeTiempo(PeriodoDeTiempo peridoDeTiempo){
		this.periodoDeTiempo = peridoDeTiempo;
	}

	public PeriodoDeTiempo getPeridoDeTiempo(){
		return this.periodoDeTiempo;
	}

	public LocalDate fechaDeInicio(){
		return this.getPeridoDeTiempo().getFechaInicio();
	}

	public LocalDate fechaFin(){
		return this.getPeridoDeTiempo().getFechaFin();
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

	public void agregarAlCarrito(Servicio servicio) {
		//his.carrito.addServicio(servicio, this.fechaInicio, this.fechaFin, this.cantidadDePasajeros);
		if (!servicio.hayDisponibilidad(this.getPeridoDeTiempo(), this.getCantidadPasajeros())) {
			throw new IllegalStateException("No hay cupos disponibles para este servicio en las fechas seleccionadas");
		} else {
			this.lista.add(servicio);
		}
	}

	public void eliminarDelCarrito(Servicio servicio) {
		//this.carrito.removeServicio(servicio);
		this.lista.remove(servicio);
	}

	public void confirmarCarrito() {
		//this.carrito.confirmar(getFechaInicio(), getFechaFin(), getCantidadPasajeros());
		this.lista.forEach(producto -> producto.confirmar(this.getPeridoDeTiempo(), this.getCantidadPasajeros()));
	}

	public void cancelarCarrito() {
		//this.carrito.cancelar(getFechaInicio(), getFechaFin(), getCantidadPasajeros());
		this.lista.forEach(producto -> producto.cancelar(this.getPeridoDeTiempo(), this.getCantidadPasajeros()));
        this.lista.clear();
	}

	public double costoDeServicio() {
		//return this.carrito.costoDeServicio();
		return this.lista.stream()
            .mapToDouble(producto -> producto.precioFinal())
            .sum();
	}

	private double tasasAdministrativas() {
		//return this.carrito.tasasAdministrativas();
		return 0;  //falta esta logica
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
		//return this.carrito.getListaDelCarrito();
		return this.lista;
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
 