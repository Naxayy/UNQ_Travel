package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class Confirmada extends Estado {

	public Confirmada() {}

	@Override
	public void enCheckin(Reserva reserva) {
		EnCheckin enCheckin = new EnCheckin();
		reserva.cambiarAEstado(enCheckin);
	}

	@Override
	public void cancelada(Reserva reserva) {
		Cancelada cancelada = new Cancelada();
		reserva.cambiarAEstado(cancelada);
		reserva.cancelarCarrito();
	}
}
