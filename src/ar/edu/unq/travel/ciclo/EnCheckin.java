package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCheckin extends Estado {

	@Override
	public void enCurso(Reserva reserva) {
		reserva.cambiarAEstado(new EnCurso());
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.cambiarAEstado(new Cancelada());
		
	}

}
