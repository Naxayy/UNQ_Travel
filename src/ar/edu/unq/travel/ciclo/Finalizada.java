package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.notificaciones.Fidelizacion;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;

public class Finalizada extends Estado {
    public Finalizada() {}

    @Override
	public void visitarNotificadorDeEmail(NotificadorDeEmail notificador){
		notificador.enviarMail();
	}

    @Override
	public void visitarFidelizacion(Fidelizacion fidelizacion){
		fidelizacion.generarCupo();
	}

}
