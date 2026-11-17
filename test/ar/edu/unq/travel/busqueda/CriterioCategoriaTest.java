package ar.edu.unq.travel.busqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.servicios.Servicio;

class CriterioCategoriaTest {
    CriterioCategoria miCriterio;
    Categoria unaCategoria;
    Servicio alojamiento;
    Servicio vuelo;

    @BeforeEach 
    public void startUp() {
        this.unaCategoria = mock(Categoria.class);
        this.miCriterio = new CriterioCategoria(unaCategoria);
        this.alojamiento = mock(Servicio.class);
        this.vuelo = mock(Servicio.class);
    }

    @Test
    void servicioSatisfaceCriterio() {
        when(alojamiento.contieneCategoria(unaCategoria)).thenReturn(true);
        when(vuelo.contieneCategoria(unaCategoria)).thenReturn(false);

        assertTrue(miCriterio.satisface(alojamiento));
        assertEquals(miCriterio.satisface(vuelo), false);
    }
}
