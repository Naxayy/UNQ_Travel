package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCheckin extends Estado {

	public EnCheckin() {}

	@Override
	public void enCurso(Reserva reserva) {
		EnCurso enCurso = new EnCurso();
		reserva.cambiarAEstado(enCurso);
	}

	@Override
	public void cancelada(Reserva reserva) {
		Cancelada cancelada = new Cancelada();
		reserva.cambiarAEstado(cancelada);
	}
}
