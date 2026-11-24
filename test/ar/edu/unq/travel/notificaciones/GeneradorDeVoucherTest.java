package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class GeneradorDeVoucherTest {

    private Reserva reserva;
    private GeneradorDeVoucher generador;

    @BeforeEach
    public void setUp() {
        reserva = mock(Reserva.class);
        generador = new GeneradorDeVoucher(reserva);
    }

    @Test
    public void testGetYSetReserva() {
        assertEquals(reserva, generador.getReserva());

        Reserva nuevaReserva = mock(Reserva.class);
        generador.setReserva(nuevaReserva);

        assertEquals(nuevaReserva, generador.getReserva());
    }

    @Test
    public void testAcceptDelegaEnVisitarGeneradorDeVoucherDelEstado() {
        Estado estado = mock(Estado.class);

        generador.accept(estado);

        verify(estado).visitarGeneradorDeVoucher(generador);
    }

    @Test
    public void testGenerarVoucherUsaMontoDelEstadoAnterior() {
        Estado estadoAnterior = mock(Estado.class);
        when(reserva.getEstadoAnterior()).thenReturn(estadoAnterior);
        when(estadoAnterior.monto(reserva)).thenReturn(3500.0);

        Voucher voucher = generador.generarVoucher();

        assertNotNull(voucher);
        assertEquals(3500.0, voucher.getMonto()); // si Voucher es record, cambiá getMonto() por monto()
        verify(estadoAnterior).monto(reserva);
    }
}