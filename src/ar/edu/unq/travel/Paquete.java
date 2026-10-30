package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

public class Paquete implements Servicio {
	private List<Servicio> servicios =  new ArrayList<>();
	private String nombre;
	private String descripcion;
	private int descuento;
	
	public Paquete(String nombre, String descripcion, int descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
	}
	
	@Override
	public String nombre() {
		return nombre;
	}

	@Override
	public String descripcion() {
		return descripcion;
	}

	@Override
	public Double precioBase() {
		Double totalSuma = servicios.stream().mapToDouble(Servicio::precioFinal).sum();
		return totalSuma;
	}
	
	public void agregarItem(Servicio item) {
		servicios.add(item);
	}
	
	public void quitarItem(Servicio item) {
		servicios.remove(item);
	}

	@Override
	public Double precioFinal() {
		return precioBase() * (1.0 - (this.descuento / 100.0));
	}
	
}
