package ar.edu.unq.travel.servicios;

import java.util.Date;
import java.util.Map;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private int cuposDisponibles;
	//private Map<Date, Integer> cupoDisponible;
	//private List<AtributoDinamico> atributoDinamico = new ArrayList<AtributoDinamico>
	
	public Servicio(String nombre, String descripcion, int cuposDisponibles) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cuposDisponibles = cuposDisponibles;
    }

	public boolean hayDisponibilidad() {
		return cuposDisponibles > 0;
	};

	public void decrementarCupo() {
		if (this.hayDisponibilidad()) {
			cuposDisponibles--;
		}
	}

	public void incrementarCupo() {
		cuposDisponibles++;
	}

	public abstract boolean contieneCategoria(Categoria categoria);
	
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
}

