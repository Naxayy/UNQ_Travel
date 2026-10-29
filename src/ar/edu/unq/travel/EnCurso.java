package ar.edu.unq.travel;

public class EnCurso extends Estado {

	@Override
	public void finalizada(Reserva reserva) {
		reserva.cambiarAEstado(new Finalizada());
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}
