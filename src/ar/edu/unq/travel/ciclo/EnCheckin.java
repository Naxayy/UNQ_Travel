package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;

public class EnCheckin extends Estado {

	public EnCheckin() {}

	@Override
	public void enCurso(Reserva reserva) {
		EnCurso enCurso = new EnCurso();
		reserva.cambiarAEstado(enCurso);
	}

	@Override
	public void cancelada(Reserva reserva) {
		CanceladaTest cancelada = new CanceladaTest();
		reserva.cambiarAEstado(cancelada);
		reserva.cancelarCarrito();
	}

	@Override 
	public boolean esGeneradorDeVoucher(){
		return true;
	}

	@Override 
	public double montoVoucher(Reserva reserva){
		return reserva.costoDeServicioConTasas();
	}
	
}
