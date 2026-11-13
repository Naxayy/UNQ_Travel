package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCheckin extends Estado {

	public EnCheckin() {}

	@Override
	public void enCurso(Reserva reserva) {
		EnCursoTest enCurso = new EnCursoTest();
		reserva.cambiarAEstado(enCurso);
	}

	@Override
	public void cancelada(Reserva reserva) {
		Cancelada cancelada = new Cancelada();
		reserva.cambiarAEstado(cancelada);
	}
}
