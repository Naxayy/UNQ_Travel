package ar.edu.unq.travel.busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioPrecioMaximoTest {

    private CriterioPrecioMaximo criterio;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        criterio = new CriterioPrecioMaximo(5000.0);
        servicio = mock(Servicio.class);
    }

    @Test
    public void testSatisfaceDevuelveTrueCuandoElPrecioEsMenorAlMaximo() {
        when(servicio.precioFinal()).thenReturn(3500.0);

        assertTrue(criterio.satisface(servicio));
        verify(servicio).precioFinal();
    }

    @Test
    public void testSatisfaceDevuelveTrueCuandoElPrecioEsIgualAlMaximo() {
        when(servicio.precioFinal()).thenReturn(5000.0);

        assertTrue(criterio.satisface(servicio));
        verify(servicio).precioFinal();
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoElPrecioSuperaElMaximo() {
        when(servicio.precioFinal()).thenReturn(5000.01);

        assertFalse(criterio.satisface(servicio));
        verify(servicio).precioFinal();
    }
}