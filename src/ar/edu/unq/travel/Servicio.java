package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private List<AtributoDinamico> atributosDinamicos = new ArrayList<AtributoDinamico>();
	
	public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
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

