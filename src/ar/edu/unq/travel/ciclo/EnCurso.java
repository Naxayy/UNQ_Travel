package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCurso extends Estado {
	private Reserva reserva;

	public EnCurso(Reserva reserva) {
		this.reserva = reserva;
	}

	private Reserva getReserva(){
		return this.reserva;
	}

	@Override
	public void finalizada() {
		this.getReserva().cambiarAEstado(new Finalizada());
	}

	@Override
	public void cancelada() {
		this.getReserva().cambiarAEstado(new Cancelada());
		//tengo que generar el voucher de costos de servicios no disfrutados
	}

}
