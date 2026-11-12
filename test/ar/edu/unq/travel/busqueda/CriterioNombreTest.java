package ar.edu.unq.travel.busqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.servicios.Servicio;

class CriterioNombreTest {
    CriterioNombre miCriterioNombre;
    Servicio vuelo;

    @BeforeEach
    public void startUp() {
        this.miCriterioNombre = new CriterioNombre("miami");
        this.vuelo = mock(Servicio.class);
    }

    @Test
    void satisfaceLaBusqueda() {
        when(vuelo.getNombre()).thenReturn("Vuelo a Miami");
        

        assertTrue(miCriterioNombre.satisface(vuelo));
    }

    @Test
    void noSatisfaceLaBusqueda() {
        when(vuelo.getNombre()).thenReturn("Vuelo a Cancun");
        

        assertEquals(miCriterioNombre.satisface(vuelo), false);
    }
}
