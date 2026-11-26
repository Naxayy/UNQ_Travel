package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;

public class EnCheckinTest {

    private EnCheckin estado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        estado = new EnCheckin();
        reserva = mock(Reserva.class);
    }

    @Test
    public void testEnCursoCambiaEstadoAEnCurso() {
        estado.enCurso(reserva);

        verify(reserva).cambiarAEstado(any(EnCurso.class));
    }

    @Test
    public void testCanceladaCancelaCarritoYCambiaEstadoACancelada() {
        estado.cancelada(reserva);

        InOrder orden = inOrder(reserva);
        orden.verify(reserva).cancelarCarrito();
        orden.verify(reserva).cambiarAEstado(any(Cancelada.class));
    }

    @Test
    public void testMontoDevuelveCostoDeServicioConTasas() {
        when(reserva.costoDeServicioConTasas()).thenReturn(2500.0);

        assertEquals(2500.0, estado.monto(reserva));
    }

    @Test
    public void testTransicionInvalidaLanzaExcepcion() {
        assertThrows(IllegalStateException.class, () -> estado.enCheckin(reserva));
    }
}