package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.notificaciones.GeneradorDeVoucher;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;

public class ConfirmadaTest {

    private Confirmada estado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        estado = new Confirmada();
        reserva = mock(Reserva.class);
    }
    
    @Test
    public void testEnCheckinCambiaEstadoAEnCheckin() {
        estado.enCheckin(reserva);

        verify(reserva).cambiarAEstado(any(EnCheckin.class));
    }

    @Test
    public void testVisitarNotificadorDeEmail() {
        NotificadorDeEmail notificador = mock(NotificadorDeEmail.class);

        estado.visitarNotificadorDeEmail(notificador);

        verify(notificador).enviarMail();
    }

    @Test
    public void testCancelarCambiaEstadoACancelada() {
        estado.cancelada(reserva);

        verify(reserva).cambiarAEstado(any(Cancelada.class));
    }

    @Test
    public void testTransicionesInvalidasLanzanExcepcion() {
        assertThrows(IllegalStateException.class, () -> estado.confirmar(reserva));
        assertThrows(IllegalStateException.class, () -> estado.enCurso(reserva));
        assertThrows(IllegalStateException.class, () -> estado.finalizada(reserva));
    }
}