package ar.edu.unq.travel.busqueda;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.servicios.Servicio;

public class CriterioDisponibilidadTest {

    private PeriodoDeTiempo periodo;
    private CriterioDisponibilidad criterio;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        periodo = new PeriodoDeTiempo(LocalDate.of(2026, 11, 10),LocalDate.of(2026, 11, 15));
        criterio = new CriterioDisponibilidad(periodo, 2);
        servicio = mock(Servicio.class);
    }

    @Test
    public void testSatisfaceDevuelveTrueCuandoElServicioTieneDisponibilidad() {
        when(servicio.hayDisponibilidad(periodo, 2)).thenReturn(true);

        assertTrue(criterio.satisface(servicio));
        verify(servicio).hayDisponibilidad(periodo, 2);
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoElServicioNoTieneDisponibilidad() {
        when(servicio.hayDisponibilidad(periodo, 2)).thenReturn(false);

        assertFalse(criterio.satisface(servicio));
        verify(servicio).hayDisponibilidad(periodo, 2);
    }
}