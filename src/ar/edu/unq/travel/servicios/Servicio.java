package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;
import ar.edu.unq.travel.categoria.*;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private Map<LocalDate, Integer> cuposDisponibles;
	private List<AtributoDinamico> atributoDinamico;
	
	public Servicio(String nombre, String descripcion, Map<LocalDate, Integer> cuposDisponibles) {
        this.nombre = nombre;
        this.descripcion = descripcion;
		this.atributoDinamico = new ArrayList<>();
		this.cuposDisponibles = cuposDisponibles;
    }

	public boolean hayDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, int cantidadPersonas) {
		for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
			if (!hayDisponibilidadEnFecha(fecha, cantidadPersonas)) {
				return false;
			}
		}
		return true;
	}

	public boolean hayDisponibilidadEnFecha(LocalDate fecha, int cantidad) {
		return cuposDisponibles.getOrDefault(fecha, 0) >= cantidad;
	}

	public void decrementarCupo(LocalDate fecha, int cantidad) {
		if (!hayDisponibilidadEnFecha(fecha, cantidad)) {
        	throw new IllegalStateException("No hay cupos suficientes");
    	}
    	cuposDisponibles.put(fecha, cuposDisponibles.get(fecha) - cantidad);
	}

	public void incrementarCupo(LocalDate fecha, int cantidad) {
		cuposDisponibles.put(fecha, cuposDisponibles.get(fecha) + cantidad);;
	}

	public abstract boolean contieneCategoria(Categoria categoria);
	
	// podria agregar tasas admi a cada servicio y que las sume 
	
	public abstract double precioBase();
	
	public abstract double precioFinal();

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void confirmar(){
		this.decrementarCupo(1);
	}

	public void cancelar(){
		this.cuposDisponibles = cuposDisponibles +1;
	}
}

