package ar.edu.unq.travel.notificaciones;

public interface MailSender {
    
    void enviarMail(String direccionDestino, String titulo, String mensaje, Object adjunto);
}   