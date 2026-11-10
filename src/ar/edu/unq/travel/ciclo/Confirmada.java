package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class Confirmada extends Estado {

	public void Confirmada(){}

	@Override
	public void enCheckin(Reserva reserva) {
		reserva.cambiarAEstado(new EnCheckin());
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.cambiarAEstado(new Cancelada());
		reserva.cancelarCarrito();
	}

	@Override 
	public void validarEstado(){
		enviarMail();
	}
}
