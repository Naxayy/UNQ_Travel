package ar.edu.unq.travel;

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
