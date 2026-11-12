package ar.edu.unq.travel.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito.*;

import ar.edu.unq.travel.categoria.*;
import ar.edu.unq.travel.servicios.*;

class CriterioCategoriaTest {
    CriterioCategoria miCriterio;
    Categoria unaCategoria;
    Servicio alojamiento;
    Servicio vuelo;

    @BeforeEach 
    public void startUp() {
        this.miCriterio = new CriterioCategoria(unaCategoria);
        this.unaCategoria = mock(Categoria.class);
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
