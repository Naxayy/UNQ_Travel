package ar.edu.unq.travel.traslados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;

class TrasladoEstandarTest {

    private TrasladoEstandar traslado; 

    private PlataformaTransporte plataforma; 
    private Reserva reserva;
    private Direccion direccion;

    @BeforeEach
    void setUp() {
        plataforma = mock(PlataformaTransporte.class);
        reserva = mock(Reserva.class);
        direccion = mock(Direccion.class);

        traslado = new TrasladoEstandar(plataforma);
    }

    @Test
    void testCalcularCostoDelegaElCalculoALaPlataforma() {

        when(reserva.getCantPersonas()).thenReturn(3);
        when(reserva.getDireccion()).thenReturn(direccion);

        when(plataforma.estimarTraslado(3, direccion)).thenReturn(15000f);

        double costo = traslado.calcularCosto(reserva);

        assertEquals(15000.0, costo);
        verify(plataforma).estimarTraslado(3, direccion);
    }
}
