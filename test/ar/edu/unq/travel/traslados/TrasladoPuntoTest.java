package ar.edu.unq.travel.traslados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;

public class TrasladoPuntoTest {

    private TrasladoPunto traslado;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        traslado = new TrasladoPunto();
        reserva = mock(Reserva.class);
    }

    @Test
    public void testEsSubclaseDeTraslado() {
        assertInstanceOf(Traslado.class, traslado);
    }

    @Test
    public void testCalcularCostoDevuelveCeroSinConsultarLaReserva() {
        double costo = traslado.calcularCosto(reserva);

        assertEquals(0.0, costo, 0.001);
        verifyNoInteractions(reserva);
    }
}