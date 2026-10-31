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

	//decidimos tomar precio base como la suma de todos los precio base de los servicios
	public Double precioBase() {
		return servicios.stream().mapToDouble(Servicio::precioFinal).sum();;
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
