package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.ciclo.Confirmada;
import ar.edu.unq.travel.ciclo.EnCurso;
import ar.edu.unq.travel.ciclo.Finalizada;

public class NotificadorDeEmail implements Observer {
    private MailSender mail;
    private String direccionDestino;
    private Object adjunto;

    public NotificadorDeEmail(MailSender mail, String direccionDestino) {
        this.mail = mail;
        this.direccionDestino = direccionDestino;
    }

    @Override
    public void update(Observable observable) {
        if (this.validarEstado(observable)) {
            mail.enviarMail(this.direccionDestino, "Actualización de reserva", "Su reserva ha sido actualizada a " + observable.getEstado().toString() + ".", adjunto);
        }
    }

    private boolean validarEstado(Observable observable) {
        return observable.getEstado() instanceof Confirmada || observable.getEstado() instanceof EnCurso || observable.getEstado() instanceof Finalizada;
    }
}
