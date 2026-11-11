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
		Confirmada confirmada = new Confirmada(this.getReserva());
		this.getReserva().cambiarAEstado(confirmada);
	}

	@Override
	public void cancelada() {
		this.getReserva().cambiarAEstado(new Cancelada());
		this.getReserva().cancelarCarrito();
	}
}
