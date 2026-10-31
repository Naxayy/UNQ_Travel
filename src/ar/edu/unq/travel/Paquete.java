package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Servicio {
	private List<Servicio> servicios =  new ArrayList<>();
	private int descuento;
	
	public Paquete(String nombre, String descripcion, int descuento) {
		super(nombre, descripcion);
		this.descuento = descuento;
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
