package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCurso extends Estado {

	public EnCurso() {}

	@Override
	public void finalizada(Reserva reserva) {
		Finalizada finalizada = new Finalizada();
		reserva.cambiarAEstado(finalizada);
	}

	@Override
	public void cancelada(Reserva reserva) {
		Cancelada cancelada = new Cancelada();
		reserva.cambiarAEstado(cancelada);
	}

}
