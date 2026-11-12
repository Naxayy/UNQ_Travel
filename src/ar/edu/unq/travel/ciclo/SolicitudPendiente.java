package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class SolicitudPendiente extends Estado {
	
	public SolicitudPendiente() {}

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
		Confirmada confirmada = new Confirmada();
		reserva.cambiarAEstado(confirmada);
		reserva.confirmarCarrito();
	}	
	
	@Override
	public void cancelada(Reserva reserva) {
		Cancelada cancelada = new Cancelada();
		reserva.cambiarAEstado(cancelada);
	}
}
