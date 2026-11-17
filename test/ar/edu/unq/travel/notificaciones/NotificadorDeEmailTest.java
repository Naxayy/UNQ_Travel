package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class NotificadorDeEmailTest {

    private MailSender mailSender;
    private Reserva reserva;
    private Estado estado;
    private NotificadorDeEmail notificador;

    @BeforeEach
    public void setUp() {
        mailSender = mock(MailSender.class);
        reserva = mock(Reserva.class);
        estado = mock(Estado.class);
        when(reserva.getEstado()).thenReturn(estado);
        notificador = spy(new NotificadorDeEmail(mailSender, "cliente@unq.edu.ar", reserva));
    }

    @Test
    public void testGetYSetReserva() {
        assertEquals(reserva, notificador.getReserva());

        Reserva otraReserva = mock(Reserva.class);
        notificador.setReserva(otraReserva);

        assertEquals(otraReserva, notificador.getReserva());
    }

    @Test
    public void testActualizarCuandoEsGeneradorDeEmailEnviaElCorreo() {
        when(estado.esGeneradorDeEmail()).thenReturn(true);

        notificador.actualizar();

        verify(notificador).enviarMail();
    }

    @Test
    public void testActualizarCuandoNoEsGeneradorDeEmailNoEnviaElCorreo() {
        when(estado.esGeneradorDeEmail()).thenReturn(false);

        notificador.actualizar();

        verify(notificador, never()).enviarMail();
    }
}