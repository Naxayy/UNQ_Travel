package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class ObsNotificadorDeEmail implements Observador {
    private Reserva reserva;
    private MailSender mail;
    private String direccionDestino;
    private Object adjunto;

    public ObsNotificadorDeEmail(MailSender mail, String direccionDestino,Reserva reserva) {
        this.reserva= reserva;
        this.mail = mail;
        this.direccionDestino = direccionDestino;
    }

    @Override
    public void actualizar() {
        Estado estado = reserva.getEstado();
        if(estado.esGeneradorDeEmail()){
            this.enviarMail();
        }

    }

    public void enviarMail(){
        //mail.enviarMail(this.direccionDestino, "Actualización de reserva", "Su reserva ha sido actualizada a " + observable.getEstado().toString() + ".", adjunto);
    }
}
