package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.notificaciones.Fidelizacion;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;

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

	@Override
	public void visitarNotificadorDeEmail(NotificadorDeEmail notificador){
		notificador.enviarMail();
	}

	@Override 
	public double monto(Reserva reserva){
		return reserva.costoDeServiciosNoDisfrutados();
	}

}
