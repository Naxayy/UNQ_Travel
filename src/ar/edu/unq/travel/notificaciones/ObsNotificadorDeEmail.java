package ar.edu.unq.travel.notificaciones;

public class ObsNotificadorDeEmail implements Observador {
    private MailSender mail;
    private String direccionDestino;
    private Object adjunto;

    public ObsNotificadorDeEmail(MailSender mail, String direccionDestino) {
        this.mail = mail;
        this.direccionDestino = direccionDestino;
    }

    @Override
    public void actualizar() {
    }

    public void enviarMail(){
        //mail.enviarMail(this.direccionDestino, "Actualización de reserva", "Su reserva ha sido actualizada a " + observable.getEstado().toString() + ".", adjunto);
    }
}
