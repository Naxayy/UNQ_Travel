package ar.edu.unq.travel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.servicios.Servicio;


class CarritoTest {
    
    Carrito miCarrito;

    Servicio vuelo;
    Servicio alojamiento;
    Servicio paquete;
    int cantPersonas = 1;
    LocalDate inicio = LocalDate.of(2005,12,7);
    LocalDate fin = LocalDate.of(2005,12,15);

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
        when(this.vuelo.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        when(this.alojamiento.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        when(this.paquete.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);


        this.miCarrito.addServicio(this.vuelo, inicio, fin, cantPersonas);
        this.miCarrito.addServicio(this.alojamiento, inicio, fin, cantPersonas);
        this.miCarrito.addServicio(this.paquete, inicio, fin, cantPersonas);

        assertTrue(this.miCarrito.contieneElServicio(this.vuelo));
        assertTrue(this.miCarrito.contieneElServicio(this.alojamiento));
        assertTrue(this.miCarrito.contieneElServicio(this.paquete));
    }

    @Test
    void testSacarServiciosDelCarritoLoEliminaDeLista() {
        when(this.vuelo.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);

        this.miCarrito.addServicio(this.vuelo,inicio,fin,cantPersonas);
        this.miCarrito.removeServicio(this.vuelo);

        assertFalse(this.miCarrito.contieneElServicio(this.vuelo));
    }

    @Test
    void testConfirmarCarritoConfirmaLosProductosDeSuLista() {
        when(this.vuelo.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);

        this.miCarrito.addServicio(this.vuelo,inicio,fin,cantPersonas);

        this.miCarrito.confirmar(inicio, fin , cantPersonas);

        verify(this.vuelo).confirmar(inicio, fin , cantPersonas);
    }

    @Test
    void testCancelarCarritoCancelaLosProductosYDejaSuListaVacia() {
        when(this.vuelo.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);

        this.miCarrito.addServicio(this.vuelo,inicio,fin,cantPersonas);

        this.miCarrito.confirmar(inicio, fin , cantPersonas);
        this.miCarrito.cancelar(inicio, fin , cantPersonas);

        verify(this.vuelo).cancelar(inicio, fin , cantPersonas);
        assertTrue(this.miCarrito.getListaDelCarrito().isEmpty());
    }

    //TASAS
    @Test 
    void testTasasAdministrativas() {
        //Las tasas estan predefinidas de forma arbitraria
        assertEquals(0,miCarrito.tasasAdministrativas());
    }

    @Test
    void testCostoDelServiciosSumaElPrecioFinalDeTodosLosServicios() {
        when(this.vuelo.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        when(this.alojamiento.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        when(this.paquete.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);

        miCarrito.addServicio(vuelo,inicio,fin,cantPersonas);
        miCarrito.addServicio(alojamiento,inicio,fin,cantPersonas);
        miCarrito.addServicio(paquete,inicio,fin,cantPersonas);

        when(vuelo.precioFinal()).thenReturn(10.0);
        when(alojamiento.precioFinal()).thenReturn(16.4);
        when(paquete.precioFinal()).thenReturn(7.99);

        assertEquals(34.39, miCarrito.costoDeServicio(),0.001);
    }

    @Test
    void testListaDelCarrito() {
        when(this.vuelo.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        when(this.alojamiento.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        when(this.paquete.hayDisponibilidad(inicio, fin, cantPersonas)).thenReturn(true);
        
        miCarrito.addServicio(vuelo,inicio,fin,cantPersonas);
        miCarrito.addServicio(alojamiento,inicio,fin,cantPersonas);
        miCarrito.addServicio(paquete,inicio,fin,cantPersonas);

        assertFalse(this.miCarrito.getListaDelCarrito().isEmpty());
    }
    
}
