package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class Confirmada extends Estado {
	private Reserva reserva;

	public Confirmada(Reserva reserva) {
		this.reserva = reserva;
	}

	private Reserva getReserva(){
		return this.reserva;
	}

	@Override
	public void enCheckin() {
		this.getReserva().cambiarAEstado(new EnCheckin());
	}

	@Override
	public void cancelada() {
		this.getReserva().cambiarAEstado(new Cancelada());
		this.getReserva().cancelarCarrito();
	}
}
