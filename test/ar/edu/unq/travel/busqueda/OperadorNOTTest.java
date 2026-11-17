package ar.edu.unq.travel.busqueda;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.servicios.Servicio;

public class OperadorNOTTest {

    private CriterioBusqueda criterio1;
    private CriterioBusqueda criterio2;
    private OperadorNOT operadorNot;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        criterio1 = mock(CriterioBusqueda.class);
        criterio2 = mock(CriterioBusqueda.class);
        operadorNot = new OperadorNOT(List.of(criterio1, criterio2));
        servicio = mock(Servicio.class);
    }

    @Test
    public void testSatisfaceDevuelveTrueCuandoNingunCriterioSeCumple() {
        when(criterio1.satisface(servicio)).thenReturn(false);
        when(criterio2.satisface(servicio)).thenReturn(false);

        assertTrue(operadorNot.satisface(servicio));
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoAlMenosUnCriterioSeCumple() {
        when(criterio1.satisface(servicio)).thenReturn(false);
        when(criterio2.satisface(servicio)).thenReturn(true);

        assertFalse(operadorNot.satisface(servicio));
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoTodosLosCriteriosSeCumplen() {
        when(criterio1.satisface(servicio)).thenReturn(true);
        when(criterio2.satisface(servicio)).thenReturn(true);

        assertFalse(operadorNot.satisface(servicio));
    }
}