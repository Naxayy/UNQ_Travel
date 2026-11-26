package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;

public class EnCursoTest {

    private EnCurso estado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        estado = new EnCurso();
        reserva = mock(Reserva.class);
    }

    @Test
    public void testFinalizadaCambiaEstadoAFinalizada() {
        estado.finalizada(reserva);

        verify(reserva).cambiarAEstado(any(Finalizada.class));
    }

    @Test
    public void testCanceladaCambiaEstadoACancelada() {
        estado.cancelada(reserva);

        verify(reserva).cambiarAEstado(any(Cancelada.class));
    }

    @Test
    public void testVisitarNotificadorDeEmailEnviaMail() {
        NotificadorDeEmail notificador = mock(NotificadorDeEmail.class);

        estado.visitarNotificadorDeEmail(notificador);

        verify(notificador).enviarMail();
    }

    @Test
    public void testMontoDevuelveCostoDeServiciosNoDisfrutados() {
        when(reserva.costoDeServiciosNoDisfrutados()).thenReturn(1800.0);

        assertEquals(1800.0, estado.monto(reserva));
    }

    @Test
    public void testTransicionesInvalidasLanzanExcepcion() {
        assertThrows(IllegalStateException.class, () -> estado.enCheckin(reserva));
        assertThrows(IllegalStateException.class, () -> estado.enCurso(reserva));
    }
}