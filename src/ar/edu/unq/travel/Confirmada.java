package ar.edu.unq.travel;

public class Confirmada extends Estado {

	@Override
	public void enCheckin(Reserva reserva) {
		reserva.cambiarAEstado(new EnCheckin());
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}
