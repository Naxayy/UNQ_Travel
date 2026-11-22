package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.categoria.Categoria;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private Map<LocalDate, Integer> cuposDisponibles;
	
	public Servicio(String nombre, String descripcion, Map<LocalDate, Integer> cuposDisponibles) {
        this.nombre = nombre;
        this.descripcion = descripcion;
		this.cuposDisponibles = cuposDisponibles;
    }
	
	// valida si hay disponibilidad desde fechaInicio hasta fechaFin para una cierta cantidadPersonas
	public abstract boolean hayDisponibilidad(PeriodoDeTiempo periodo, int cantidadPersonas);
	
	//decrementa en una fecha, una cantidad de cupos segun cantidad de persoans
	public abstract void decrementarCupo(LocalDate fecha, int cantidad);

	//incrementa en una fecha, una cantidad de cupos segun cantidad de personas
	public abstract void incrementarCupo(LocalDate fecha, int cantidad);

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

	protected Map<LocalDate, Integer> getCuposDisponibles() {
		return this.cuposDisponibles;
	}

	protected void setCuposDisponibles(LocalDate fecha, int cantidad) {
		cuposDisponibles.put(fecha, cantidad);
	}

	//arreglar
	public void confirmar(PeriodoDeTiempo periodo, int cantidadPersonas){
		for (LocalDate fecha = periodo.getFechaInicio(); !fecha.isAfter(periodo.getFechaFin()); fecha = fecha.plusDays(1)) {
			decrementarCupo(fecha, cantidadPersonas);
		}
	}

	public void cancelar(PeriodoDeTiempo periodo, int cantidadPersonas){
		for (LocalDate fecha = periodo.getFechaInicio(); !fecha.isAfter(periodo.getFechaFin()); fecha = fecha.plusDays(1)) {
			incrementarCupo(fecha, cantidadPersonas);
		}
	}

	//###### REPORTES #####

	public int cuposDisponiblesEnPeriodo(PeriodoDeTiempo periodo) {
		if (this.cuposDisponibles == null) {
			return 0;
		}

		int libres = 0;

		for (Map.Entry<LocalDate, Integer> entrada : this.cuposDisponibles.entrySet()) {
			LocalDate fecha = entrada.getKey();

			if (!fecha.isBefore(periodo.getFechaInicio()) && !fecha.isAfter(periodo.getFechaFin())) {
				libres += entrada.getValue();
			}
		}

		return libres;
	}

	public abstract void recolectarServiciosIndividuales(Set<Servicio> resultado);

	public abstract void agregarProveedoresA(Set<Proveedor> proveedores);

	public abstract boolean esDeProveedor(Proveedor proveedor);
}

