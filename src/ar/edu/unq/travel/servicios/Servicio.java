package ar.edu.unq.travel.servicios;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private Cupo cupos;
	//private List<AtributoDinamico> atributoDinamico = new ArrayList<AtributoDinamico>
	
	public Servicio(String nombre, String descripcion, Disponibilidad cupos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cupos = cupos;
    }

	public boolean hayDisponibilidad(Date fecha) {
		return ;
	};

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

