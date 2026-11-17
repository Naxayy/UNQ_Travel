package ar.edu.unq.travel.traslados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;

public class TrasladoVipTest {

    private TrasladoVip traslado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        traslado = new TrasladoVip(2, 500.0);
        reserva = mock(Reserva.class);
    }

    @Test
    public void testEsSubclaseDeTraslado() {
        assertInstanceOf(Traslado.class, traslado);
    }

    @Test
    public void testCalcularCostoMultiplicaCostoDeServicioPorPorcentajeYSumaCargoBase() {
        when(reserva.costoDeServicio()).thenReturn(1000.0);

        double costo = traslado.calcularCosto(reserva);

        assertEquals(2500.0, costo, 0.001);
        verify(reserva).costoDeServicio();
    }

    @Test
    public void testCalcularCostoConCostoDeServicioCeroDevuelveSoloCargoBase() {
        when(reserva.costoDeServicio()).thenReturn(0.0);

        double costo = traslado.calcularCosto(reserva);

        assertEquals(500.0, costo, 0.001);
        verify(reserva).costoDeServicio();
    }

    @Test
    public void testSetPorcentajeFijoActualizaElCalculoDelCosto() {
        when(reserva.costoDeServicio()).thenReturn(1000.0);

        traslado.setPorcentajeFijo(5);

        assertEquals(5500.0, traslado.calcularCosto(reserva), 0.001);
    }
}