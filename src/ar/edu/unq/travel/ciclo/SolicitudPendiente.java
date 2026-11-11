package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class SolicitudPendiente extends Estado {
	private Reserva reserva;

	public SolicitudPendiente(Reserva reserva) {
		this.reserva = reserva;
	}

	private Reserva getReserva(){
		return this.reserva;
	}

	@Override
	public void agregarServicio(Servicio servicio){
		this.getReserva().agregarAlCarrito(servicio);
	}

	@Override
	public void quitarServicio(Servicio servicio){
		this.getReserva().eliminarDelCarrito(servicio);
	}

	@Override
	public void confirmar() {
		this.getReserva().confirmarCarrito();

		Confirmada confirmada = new Confirmada(this.getReserva());

		this.getReserva().cambiarAEstado(confirmada);
	}	
	
	@Override
	public void cancelada() {
		this.getReserva().cambiarAEstado(new Cancelada());
	}
}
