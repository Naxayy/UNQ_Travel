package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.servicios.*;;

class CatalogoTest {
    
    Catalogo miCatalogo;
    Servicio alojamiento;
    Servicio vuelo;
    Servicio paquete;

    @BeforeEach
    public void setUp() {
        this.miCatalogo = new Catalogo();
        this.alojamiento = mock(Servicio.class);
        this.vuelo = mock(Servicio.class);
        this.paquete = mock(Servicio.class);
    }

    @Test
    void seAgreganItemsALaLista() {
        miCatalogo.agregarItem(vuelo);
        miCatalogo.agregarItem(alojamiento);
        miCatalogo.busqueda();
    }
}
