package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class EstadoTest {

    private Estado estado;
    private Reserva reserva;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        estado = new Estado() {};
        reserva = mock(Reserva.class);
        servicio = mock(Servicio.class);
    }

    @Test
    public void testConfirmarPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.confirmar(reserva));

        assertEquals("no se puede confirmar la reserva en este estado de la compra", excepcion.getMessage());
        verifyNoInteractions(reserva);
    }

    @Test
    public void testEnCheckinPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.enCheckin(reserva));

        assertEquals("no se puede realizar checkin de la reserva en este estado de la compra", excepcion.getMessage());
        verifyNoInteractions(reserva);
    }

    @Test
    public void testEnCursoPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.enCurso(reserva));

        assertEquals("no se puede poner en curso a la reserva en este estado de la compra", excepcion.getMessage());
        verifyNoInteractions(reserva);
    }

    @Test
    public void testFinalizadaPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.finalizada(reserva));

        assertEquals("no se puede finalizar la reserva en este estado de la compra", excepcion.getMessage());
        verifyNoInteractions(reserva);
    }

    @Test
    public void testCanceladaPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.cancelada(reserva));

        assertEquals("no se puede cancelar la reserva en este estado de la compra", excepcion.getMessage());
        verifyNoInteractions(reserva);
    }

    @Test
    public void testAgregarServicioPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.agregarServicio(reserva, servicio));

        assertEquals("solo se pueden agregar servicios al iniciar la reserva", excepcion.getMessage());
        verifyNoInteractions(reserva, servicio);
    }

    @Test
    public void testQuitarServicioPorDefectoLanzaExcepcion() {
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> estado.quitarServicio(reserva, servicio));

        assertEquals("solo se pueden quitar servicios al iniciar la reserva", excepcion.getMessage());
        verifyNoInteractions(reserva, servicio);
    }

    @Test
    public void testValoresPorDefectoDeConsultasDeEstado() {
        assertTrue(estado.ocupaCupo());
        assertFalse(estado.esGeneradorDeEmail());
        assertFalse(estado.esGeneradorDeVoucher());
        assertFalse(estado.esFidelizador());
        assertEquals(0.0, estado.montoVoucher(reserva));
        verifyNoInteractions(reserva);
    }
}