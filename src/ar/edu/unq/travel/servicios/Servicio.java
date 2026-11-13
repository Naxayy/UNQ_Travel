package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;
import ar.edu.unq.travel.categoria.*;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private Map<LocalDate, Integer> cupos;
	private List<AtributoDinamico> atributoDinamico;
	
	public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
		this.atributoDinamico = new ArrayList<>();
    }


	public boolean hayDisponibilidad(LocalDate fecha, int cantidad) {
		return cupos.getOrDefault(fecha, 0) >= cantidad;
	}

	public void decrementarCupo(LocalDate fecha, int cantidad) {
		if (!hayDisponibilidad(fecha, cantidad)) {
        	throw new IllegalStateException("No hay cupos suficientes");
    	}
    	cupos.put(fecha, cupos.get(fecha) - cantidad);
	}

	public void incrementarCupo(LocalDate fecha, int cantidad) {
		cupos.put(fecha, cupos.get(fecha) + cantidad);;
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

