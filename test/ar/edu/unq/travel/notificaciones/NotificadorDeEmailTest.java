package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class NotificadorDeEmailTest {

    private MailSender mail;
    private Reserva reserva;
    private NotificadorDeEmail notificador;

    @BeforeEach
    public void setUp() {
        mail = mock(MailSender.class);
        reserva = mock(Reserva.class);
        notificador = new NotificadorDeEmail(mail, "cliente@mail.com", reserva);
    }

    @Test
    public void testGetYSetReserva() {
        assertEquals(reserva, notificador.getReserva());

        Reserva nuevaReserva = mock(Reserva.class);
        notificador.setReserva(nuevaReserva);

        assertEquals(nuevaReserva, notificador.getReserva());
    }

    @Test
    public void testAcceptDelegaEnVisitarNotificadorDeEmailDelEstado() {
        Estado estado = mock(Estado.class);

        notificador.accept(estado);

        verify(estado).visitarNotificadorDeEmail(notificador);
    }

    @Test
    public void testEnviarMailNoLanzaExcepcion() {
        assertDoesNotThrow(() -> notificador.enviarMail());
    }
}