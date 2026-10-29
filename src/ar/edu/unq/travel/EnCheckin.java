package ar.edu.unq.travel;

public class EnCheckin extends Estado {

	@Override
	public void enCurso(Reserva reserva) {
		reserva.cambiarAEstado(new EnCurso());
	}

	@Override
	public void cancelada(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}
