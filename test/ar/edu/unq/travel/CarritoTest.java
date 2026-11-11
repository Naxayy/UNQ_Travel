package ar.edu.unq.travel;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unq.travel.servicios.*;

class CarritoTest {
    Carrito miCarrito;
    Servicio vuelo;
    Servicio alojamiento;
    Servicio paquete;

    @BeforeEach
    public void startUp() {
        this.miCarrito = new Carrito();
        this.vuelo = mock(Servicio.class);
        this.alojamiento = mock(Servicio.class);
        this.paquete = mock(Servicio.class);
    }

    @Test
    void agregarServicios() {
        miCarrito.addServicio(vuelo);
        miCarrito.addServicio(alojamiento);
        miCarrito.addServicio(paquete);

        assertTrue(miCarrito.getServicios().contains(vuelo));
        assertTrue(miCarrito.getServicios().contains(alojamiento));
        assertTrue(miCarrito.getServicios().contains(paquete));
    }

    @Test
    void cancelarCarrito() {
        miCarrito.addServicio(vuelo);
        miCarrito.addServicio(alojamiento);
        miCarrito.addServicio(paquete);

        miCarrito.cancelar();

        assertTrue(miCarrito.getServicios().isEmpty());
    }

    @Test 
    void tasasAdministrativas() {
        //Las tasas estan predefinidas de forma arbitraria
        assertEquals(miCarrito.tasasAdministrativas(), 0);
    }

    @Test
    void sumaDelCostoDeServicios() {
        miCarrito.addServicio(vuelo);
        miCarrito.addServicio(alojamiento);
        miCarrito.addServicio(paquete);

        when(vuelo.precioFinal()).thenReturn(10.0);
        when(alojamiento.precioFinal()).thenReturn(16.4);
        when(paquete.precioFinal()).thenReturn(7.99);

        assertEquals(miCarrito.costoDeServicio(), 34.39);
    }
}
