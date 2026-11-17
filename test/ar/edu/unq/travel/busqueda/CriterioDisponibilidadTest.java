package ar.edu.unq.travel.busqueda;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioDisponibilidadTest {

    private LocalDate inicio;
    private LocalDate fin;
    private CriterioDisponibilidad criterio;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        inicio = LocalDate.of(2026, 11, 10);
        fin = LocalDate.of(2026, 11, 15);
        criterio = new CriterioDisponibilidad(inicio, fin, 2);
        servicio = mock(Servicio.class);
    }

    @Test
    public void testSatisfaceDevuelveTrueCuandoElServicioTieneDisponibilidad() {
        when(servicio.hayDisponibilidad(inicio, fin, 2)).thenReturn(true);

        assertTrue(criterio.satisface(servicio));
        verify(servicio).hayDisponibilidad(inicio, fin, 2);
    }

    @Test
    public void testSatisfaceDevuelveFalseCuandoElServicioNoTieneDisponibilidad() {
        when(servicio.hayDisponibilidad(inicio, fin, 2)).thenReturn(false);

        assertFalse(criterio.satisface(servicio));
        verify(servicio).hayDisponibilidad(inicio, fin, 2);
    }
}