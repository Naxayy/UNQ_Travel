package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.busqueda.*;
import ar.edu.unq.travel.servicios.*;

class CatalogoTest {
    
    Catalogo miCatalogo;
    Servicio alojamiento;
    Servicio vuelo;
    Servicio paquete;
    CriterioBusqueda condicion;

    @BeforeEach
    public void setUp() {
        this.miCatalogo = new Catalogo();
        this.alojamiento = mock(Servicio.class);
        this.vuelo = mock(Servicio.class);
        this.paquete = mock(Servicio.class);
        this.condicion = mock(CriterioBusqueda.class);
    }

    @Test
    void seAgreganItemsALaLista() {
        miCatalogo.agregarItem(vuelo);
        miCatalogo.agregarItem(alojamiento);
        
        assertTrue(miCatalogo.getItems().contains(vuelo));
        assertTrue(miCatalogo.getItems().contains(alojamiento));
    }

    @Test
    void busquedaFiltrado() {
        miCatalogo.agregarItem(vuelo);
        miCatalogo.agregarItem(alojamiento);

        when(condicion.satisface(vuelo)).thenReturn(true);
        when(condicion.satisface(alojamiento)).thenReturn(false);
        
        assertEquals(
            List.of(vuelo),
            miCatalogo.busqueda(condicion)
        );
    }
}
