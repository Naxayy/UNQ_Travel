package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.notificaciones.Fidelizacion;
import ar.edu.unq.travel.notificaciones.GeneradorDeVoucher;
import ar.edu.unq.travel.notificaciones.NotificadorDeEmail;
import ar.edu.unq.travel.servicios.Servicio;

public class EstadoTest {

    private Estado estado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        estado = new Estado() {};
        reserva = mock(Reserva.class);
    }

    @Test
    public void testTransicionesDeEstadoLanzanExcepcionPorDefecto() {
        assertThrows(IllegalStateException.class, () -> estado.confirmar(reserva));
        assertThrows(IllegalStateException.class, () -> estado.enCheckin(reserva));
        assertThrows(IllegalStateException.class, () -> estado.enCurso(reserva));
        assertThrows(IllegalStateException.class, () -> estado.finalizada(reserva));
        assertThrows(IllegalStateException.class, () -> estado.cancelada(reserva));
    }

    @Test
    public void testModificarServiciosLanzaExcepcionPorDefecto() {
        Servicio servicio = mock(Servicio.class);

        assertThrows(IllegalStateException.class, () -> estado.agregarServicio(reserva, servicio));
        assertThrows(IllegalStateException.class, () -> estado.quitarServicio(reserva, servicio));
    }

    @Test
    public void testOcupaCupoPorDefectoDevuelveTrue() {
        assertTrue(estado.ocupaCupo());
    }

    @Test
    public void testMontoPorDefectoDevuelveCero() {
        assertEquals(0.0, estado.monto(reserva));
    }

    @Test
    public void testNotificacionesNoInteractuanPorDefecto() {
        GeneradorDeVoucher generador = mock(GeneradorDeVoucher.class);
        NotificadorDeEmail notificador = mock(NotificadorDeEmail.class);
        Fidelizacion fidelizacion = mock(Fidelizacion.class);

        estado.visitarGeneradorDeVoucher(generador);
        estado.visitarNotificadorDeEmail(notificador);
        estado.visitarFidelizacion(fidelizacion);

        verifyNoInteractions(generador, notificador, fidelizacion);
    }
}