package ar.edu.unq.travel;

public class Pendiente extends Estado {
	public Pendiente() {
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		reserva.cambiarAEstado(new Confirmada());
		
	}	
	
}
