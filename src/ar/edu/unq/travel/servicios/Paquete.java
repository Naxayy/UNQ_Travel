package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.edu.unq.travel.categoria.*;

public class Paquete extends Servicio {
	private List<Servicio> servicios =  new ArrayList<Servicio>();
	private int descuento;
	
	public Paquete(String nombre, String descripcion, Map<LocalDate, Integer> cuposDisponibles, int descuento, Categoria categoria) {
		super(nombre, descripcion, cuposDisponibles);
		this.descuento = descuento;
	}

	// valida si hay disponibilidad desde fechaInicio hasta fechaFin para una cierta cantidadPersonas
    @Override
	public boolean hayDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, int cantidadPersonas) {
		return servicios.stream().allMatch(servicio -> !servicio.hayDisponibilidad(fechaInicio,fechaFin,cantidadPersonas));
	}
	
	//decrementa en una fecha, una cantidad de cupos segun cantidad de persoans
	@Override
	public void decrementarCupo(LocalDate fecha, int cantidad) {
		servicios.stream().forEach(servicio -> servicio.decrementarCupo(fecha, cantidad));
	}

	//incrementa en una fecha, una cantidad de cupos segun cantidad de personas
	@Override
	public void incrementarCupo(LocalDate fecha, int cantidad) {
		servicios.stream().forEach(servicio -> servicio.incrementarCupo(fecha, cantidad));
	}

	@Override
	public boolean contieneCategoria(Categoria categoria) {
		return servicios.stream()
				.anyMatch(servicio -> servicio.contieneCategoria(categoria));
	}

	@Override
	public double precioFinal() {
		return precioBase() * (1.0 - (this.descuento / 100.0));
	}

	@Override
	//decidimos tomar precio base como la suma de todos los precio base de los servicios
	public double precioBase() {
		return servicios.stream()
				.mapToDouble(Servicio::precioBase)
				.sum();
	}
	
	public int getDescuento() {
		return descuento;
	}
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public void agregarItem(Servicio item) {
		servicios.add(item);
	}
	
	public void quitarItem(Servicio item) {
		servicios.remove(item);
	}
	
}
