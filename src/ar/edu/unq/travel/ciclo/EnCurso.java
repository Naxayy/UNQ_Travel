package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCurso extends Estado {

	@Override
	public void finalizada(Reserva reserva) {
		reserva.cambiarAEstado(new Finalizada());
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.cambiarAEstado(new Cancelada());
		//tengo que generar el voucher de costos de servicios no disfrutados
	}

}
