package ar.edu.unq.travel.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private int cuposDisponibles;
	private List<AtributoDinamico> atributoDinamico;
	
	public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
		this.atributoDinamico = new ArrayList<>();
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

	private void decrementarCupo(int cant){
		this.cuposDisponibles = cuposDisponibles - cant;
	}

	public void cancelar(){
		this.cuposDisponibles = cuposDisponibles +1;
	}
}

