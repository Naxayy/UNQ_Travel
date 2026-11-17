package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class GeneradorDeVoucherTest {

    private Reserva reserva;
    private Estado estadoActual;
    private Estado estadoAnterior;
    private GeneradorDeVoucher generador;

    @BeforeEach
    public void setUp() {
        reserva = mock(Reserva.class);
        estadoActual = mock(Estado.class);
        estadoAnterior = mock(Estado.class);
        when(reserva.getEstado()).thenReturn(estadoActual);
        generador = new GeneradorDeVoucher(reserva);
    }

    @Test
    public void testGetYSetReserva() {
        assertEquals(reserva, generador.getReserva());

        Reserva otraReserva = mock(Reserva.class);
        generador.setReserva(otraReserva);

        assertEquals(otraReserva, generador.getReserva());
    }

    @Test
    public void testActualizarCuandoEsGeneradorDeVoucherCalculaMontoDelEstadoAnterior() {
        when(estadoActual.esGeneradorDeVoucher()).thenReturn(true);
        when(reserva.getEstadoAnterior()).thenReturn(estadoAnterior);
        when(estadoAnterior.montoVoucher(reserva)).thenReturn(3500.0);

        generador.actualizar();

        verify(reserva).getEstadoAnterior();
        verify(estadoAnterior).montoVoucher(reserva);
    }

    @Test
    public void testActualizarCuandoNoEsGeneradorDeVoucherNoConsultaEstadoAnterior() {
        when(estadoActual.esGeneradorDeVoucher()).thenReturn(false);

        generador.actualizar();

        verify(reserva, never()).getEstadoAnterior();
        verify(estadoAnterior, never()).montoVoucher(reserva);
    }
}