package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCheckin extends Estado {

	public EnCheckin(){}

	@Override
	public void enCurso(Reserva reserva) {
		reserva.cambiarAEstado(new EnCurso());
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.cambiarAEstado(new Cancelada());
		reserva.cancelarCarrito();
		//devolver costo de los servicios y el de las tasas
	}



}
