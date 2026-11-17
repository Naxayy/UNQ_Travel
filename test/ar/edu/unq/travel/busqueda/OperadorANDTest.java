package ar.edu.unq.travel.busqueda;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.servicios.Servicio;

public class OperadorANDTest {

    private CriterioBusqueda criterio1;
    private CriterioBusqueda criterio2;
    private OperadorAND operadorAnd;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        criterio1 = mock(CriterioBusqueda.class);
        criterio2 = mock(CriterioBusqueda.class);
        operadorAnd = new OperadorAND(List.of(criterio1, criterio2));
        servicio = mock(Servicio.class);
    }

    @Test
    public void testSatisfaceDevuelveTrueCuandoTodosLosCriteriosSeCumplen() {
        when(criterio1.satisface(servicio)).thenReturn(true);
        when(criterio2.satisface(servicio)).thenReturn(true);

        assertTrue(operadorAnd.satisface(servicio));
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoUnCriterioNoSeCumple() {
        when(criterio1.satisface(servicio)).thenReturn(true);
        when(criterio2.satisface(servicio)).thenReturn(false);

        assertFalse(operadorAnd.satisface(servicio));
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoNingunCriterioSeCumple() {
        when(criterio1.satisface(servicio)).thenReturn(false);
        when(criterio2.satisface(servicio)).thenReturn(false);

        assertFalse(operadorAnd.satisface(servicio));
    }
}