package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.busqueda.CriterioBusqueda;
import ar.edu.unq.travel.servicios.*;;

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
}
