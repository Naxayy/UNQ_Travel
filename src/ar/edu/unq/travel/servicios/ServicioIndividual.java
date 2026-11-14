package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;
import ar.edu.unq.travel.categoria.*;

public class ServicioIndividual extends Servicio{
	private double precioBase;
	private int codigo;
	private List<AtributoDinamico> atributosDinamicos = new ArrayList<AtributoDinamico>();
	private Categoria categoria;
	
	//Cambiar atributos metodos ??
	//private Proveedor provedor; 
	
	public ServicioIndividual(String nombre, double precioBase, String descripcion, Map<LocalDate, Integer> cuposDisponibles, int codigo, Categoria categoria) {
		super(nombre, descripcion, cuposDisponibles);
		this.codigo = codigo;
		this.precioBase = precioBase;
		this.categoria = categoria;
	}

	public boolean contieneCategoria(Categoria categoria) {
		return this.categoria == categoria;
	}
	
	public ServicioIndividual(String nombre, double precioBase, String descripcion, Map<LocalDate, Integer> cuposDisponibles, int codigo, Categoria categoria, AtributoDinamico atributo) {
		super(nombre, descripcion, cuposDisponibles);
		this.codigo = codigo;
		this.precioBase = precioBase;
		this.categoria = categoria;
		this.agregarAtributo(atributo);
	}

	// valida si hay disponibilidad desde fechaInicio hasta fechaFin para una cierta cantidadPersonas
	@Override
	public boolean hayDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, int cantidadPersonas) {
		for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
			if (!hayDisponibilidadEnFecha(fecha, cantidadPersonas)) {
				return false;
			}
		}
		return true;
	}
	
	//valida se hay disponibilidad en una fecha para cierta cantidad de personas
	public boolean hayDisponibilidadEnFecha(LocalDate fecha, int cantidad) {
		return getCuposDisponibles().getOrDefault(fecha, 0) >= cantidad;
	}
	
	//decrementa en una fecha, una cantidad de cupos segun cantidad de persoans
	@Override
	public void decrementarCupo(LocalDate fecha, int cantidad) {
		if (!hayDisponibilidadEnFecha(fecha, cantidad)) {
        	throw new IllegalStateException("No hay cupos suficientes");
    	}
    	setCuposDisponibles(fecha, getCuposDisponibles().get(fecha) - cantidad);
	}

	//incrementa en una fecha, una cantidad de cupos segun cantidad de personas
	@Override
	public void incrementarCupo(LocalDate fecha, int cantidad) {
		setCuposDisponibles(fecha, getCuposDisponibles().get(fecha) + cantidad);
	}

	public void agregarAtributo(AtributoDinamico atributo){
		atributosDinamicos.add(atributo);
	}

	public List<AtributoDinamico> getAtributosDinamicos() {
		return atributosDinamicos;
	}
	
	@Override
	public double precioFinal() { 
		//decidimos tomar precio final igual que el precio base
		return this.precioBase;
	}
	
	@Override
	public double precioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public int getCodigo() {
		return codigo;
	}

}
