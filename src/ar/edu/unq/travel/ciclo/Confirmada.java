package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;

public class Confirmada extends Estado {

    public Confirmada() {}

	@Override
	public void enCheckin(Reserva reserva) {
		EnCheckin enCheckin = new EnCheckin();
		reserva.cambiarAEstado(enCheckin);
	}

    @Override
    public void cancelada(Reserva reserva) {
        Cancelada cancelada = new Cancelada();
        reserva.cancelarCarrito();
        reserva.cambiarAEstado(cancelada);
    }

    @Override
	public void visitarNotificadorDeEmail(NotificadorDeEmail notificador){
		notificador.enviarMail();
	}
}