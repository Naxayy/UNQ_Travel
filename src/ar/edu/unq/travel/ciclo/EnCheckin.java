package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCheckin extends Estado {
	private Reserva reserva;

	public EnCheckin(Reserva reserva) {
		this.reserva = reserva;
	}

	private Reserva getReserva(){
		return this.reserva;
	}

	@Override
	public void enCurso() {
		EnCurso enCurso = new EnCurso(this.getReserva());
		this.getReserva().cambiarAEstado(enCurso);
	}

	@Override
	public void cancelada() {
		this.getReserva().cambiarAEstado(new Cancelada());
		this.getReserva().cancelarCarrito();
	}



}
