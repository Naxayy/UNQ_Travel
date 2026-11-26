package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.notificaciones.Fidelizacion;
import ar.edu.unq.travel.notificaciones.GeneradorDeVoucher;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;
import ar.edu.unq.travel.servicios.Servicio;

public class CanceladaTest {

    private Cancelada estado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        estado = new Cancelada();
        reserva = mock(Reserva.class);
    }

    @Test
    public void testVisitarGeneradorDeVoucherGeneraVoucher() {
        GeneradorDeVoucher generador = mock(GeneradorDeVoucher.class);

        estado.visitarGeneradorDeVoucher(generador);

        verify(generador).generarVoucher();
    }

    @Test
    public void testOtrosObservadoresNoInteractuan() {
        NotificadorDeEmail notificador = mock(NotificadorDeEmail.class);
        Fidelizacion fidelizacion = mock(Fidelizacion.class);

        estado.visitarNotificadorDeEmail(notificador);
        estado.visitarFidelizacion(fidelizacion);

        verifyNoInteractions(notificador, fidelizacion);
    }

    @Test
    public void testMontoDevuelveCero() {
        assertEquals(0.0, estado.monto(reserva));
    }

    @Test
    public void testTransicionesYModificacionesDeServiciosLanzanExcepcion() {
        Servicio servicio = mock(Servicio.class);

        assertThrows(IllegalStateException.class, () -> estado.confirmar(reserva));
        assertThrows(IllegalStateException.class, () -> estado.enCheckin(reserva));
        assertThrows(IllegalStateException.class, () -> estado.enCurso(reserva));
        assertThrows(IllegalStateException.class, () -> estado.finalizada(reserva));
        assertThrows(IllegalStateException.class, () -> estado.cancelada(reserva));
        assertThrows(IllegalStateException.class, () -> estado.agregarServicio(reserva, servicio));
        assertThrows(IllegalStateException.class, () -> estado.quitarServicio(reserva, servicio));
    }
}