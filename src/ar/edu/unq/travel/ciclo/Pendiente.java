package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class Pendiente extends Estado {
	
	public Pendiente() {}

	@Override
	public void agregarServicio(Reserva reserva, Servicio servicio){
		reserva.agregarAlCarrito(servicio);
	}

	@Override
	public void quitarServicio(Reserva reserva, Servicio servicio){
		reserva.eliminarDelCarrito(servicio);
	}

	@Override
	public void confirmar(Reserva reserva) {
		reserva.confirmarCarrito();
		reserva.cambiarAEstado(new Confirmada());
		
	}	
	
	@Override
	public void cancelada(Reserva reserva) {
		reserva.cambiarAEstado(new Cancelada());
	}
}
