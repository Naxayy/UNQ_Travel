package ar.edu.unq.travel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unq.travel.servicios.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class CarritoTest {
    
    Carrito miCarrito;

    Servicio vuelo;
    Servicio alojamiento;
    Servicio paquete;

    @BeforeEach
    public void startUp() {

        this.vuelo = mock(Servicio.class);
        this.alojamiento = mock(Servicio.class);
        this.paquete = mock(Servicio.class);

        //SUT
        this.miCarrito = new Carrito();
    }

    @Test
    void testAgregarServicioAlCarritoLoAgregaALista() {
        this.miCarrito.addServicio(this.vuelo);
        this.miCarrito.addServicio(this.alojamiento);
        this.miCarrito.addServicio(this.paquete);

        assertTrue(this.miCarrito.contieneElServicio(this.vuelo));
        assertTrue(this.miCarrito.contieneElServicio(this.alojamiento));
        assertTrue(this.miCarrito.contieneElServicio(this.paquete));
    }

    @Test
    void testSacarServiciosDelCarritoLoEliminaDeLista() {
        this.miCarrito.addServicio(this.vuelo);
        this.miCarrito.removeServicio(this.vuelo);

        assertFalse(this.miCarrito.contieneElServicio(this.paquete));
    }

    @Test
    void testConfirmarCarritoConfirmaLosProductosDeSuLista() {

        this.miCarrito.addServicio(this.vuelo);

        this.miCarrito.confirmar();

        verify(this.vuelo).confirmar();
    }

    @Test
    void testCancelarCarritoCancelaLosProductosYDejaSuListaVacia() {

        this.miCarrito.addServicio(this.vuelo);

        this.miCarrito.confirmar();
        this.miCarrito.cancelar();

        verify(this.vuelo).cancelar();
        this.miCarrito.getListaDelCarrito().isEmpty();
    }

    //TASAS
    @Test 
    void testTasasAdministrativas() {
        //Las tasas estan predefinidas de forma arbitraria
        assertEquals(miCarrito.tasasAdministrativas(), 0);
    }

    @Test
    void testCostoDelServiciosSumaElPrecioFinalDeTodosLosServicios() {
        miCarrito.addServicio(vuelo);
        miCarrito.addServicio(alojamiento);
        miCarrito.addServicio(paquete);

        when(vuelo.precioFinal()).thenReturn(10.0);
        when(alojamiento.precioFinal()).thenReturn(16.4);
        when(paquete.precioFinal()).thenReturn(7.99);

        assertEquals(miCarrito.costoDeServicio(), 34.39);
    }

    @Test
    void testListaDelCarrito() {
        miCarrito.addServicio(vuelo);
        miCarrito.addServicio(alojamiento);
        miCarrito.addServicio(paquete);

        assertFalse(this.miCarrito.getListaDelCarrito().isEmpty());
    }
}
