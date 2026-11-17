package ar.edu.unq.travel.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.categoria.*;
import ar.edu.unq.travel.servicios.Paquete;
import ar.edu.unq.travel.servicios.ServicioIndividual;

public class PaqueteTest {

	private ServicioIndividual noche;
	private ServicioIndividual excursion;
	private ServicioIndividual trasladoAH;
	
	private Map<LocalDate, Integer> map;

	private Categoria unaCategoria;
	
	private Paquete paqueteTravel;
	private Paquete paqueteDePaquetes;
	
	@BeforeEach
	void setUp() {
		//DOC
		this.noche = mock(ServicioIndividual.class);
		this.excursion = mock(ServicioIndividual.class);
		this.trasladoAH = mock(ServicioIndividual.class);
		this.paqueteTravel = mock(Paquete.class);
		this.unaCategoria = mock(Categoria.class);
		this.map = new HashMap<LocalDate,Integer>();
		this.map.put(LocalDate.of(2004,2,10),1);
		
		//SUT
		this.paqueteDePaquetes = new Paquete ("nombre","descripcion",map,10,unaCategoria);
	}

	@Test
	void precioFinal()  {
		paqueteDePaquetes.agregarItem(noche);
		paqueteDePaquetes.agregarItem(excursion);

		paqueteTravel.agregarItem(trasladoAH);
		paqueteDePaquetes.agregarItem(paqueteTravel);

		when(noche.precioBase()).thenReturn(1200.00);
		when(excursion.precioBase()).thenReturn(400.00);
		when(paqueteTravel.precioBase()).thenReturn(200.0);

		assertEquals(1620.0, paqueteDePaquetes.precioFinal());
		verify(noche).precioBase();
	}

	@Test 
	void precioBase(){
		paqueteDePaquetes.agregarItem(noche);
		paqueteDePaquetes.agregarItem(excursion);

		when(noche.precioBase()).thenReturn(1200.00);
		when(excursion.precioBase()).thenReturn(400.00);

		assertEquals(1600.00, paqueteDePaquetes.precioBase());
		verify(noche).precioBase();
		verify(excursion).precioBase();
	}

	@Test 
	void descuentoDelPaquete(){
		assertEquals(10, paqueteDePaquetes.getDescuento());
	}

	@Test 
	void serviciosDelPaquete(){
		assertTrue(paqueteDePaquetes.getServicios().isEmpty());
	}

	@Test
	void listaDeServicios(){
		paqueteDePaquetes.agregarItem(excursion);
		paqueteDePaquetes.quitarItem(excursion);

		assertTrue(paqueteDePaquetes.getServicios().isEmpty());
	}

	@Test
	void cambiarDescuento(){
		paqueteDePaquetes.setDescuento(20);

		assertTrue(paqueteDePaquetes.getServicios().isEmpty());
	}

	@Test
    void hayDisponibilidad() {
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);
        paqueteDePaquetes.agregarItem(paqueteTravel);

        when(noche.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2)).thenReturn(true);
        when(excursion.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2)).thenReturn(true);
        when(paqueteTravel.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2)).thenReturn(true);

        assertTrue(paqueteDePaquetes.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2));
        verify(noche).hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2);
        verify(excursion).hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2);
        verify(paqueteTravel).hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2);
    }

    @Test
    void noHayDisponibilidad() {
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);

        when(noche.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2)).thenReturn(true);
        when(excursion.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2)).thenReturn(false);

        assertFalse(paqueteDePaquetes.hayDisponibilidad(LocalDate.of(2004,2,10), LocalDate.of(2004,2,15), 2));
    }

	@Test
    void decrementarCupo() {
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);
        paqueteDePaquetes.agregarItem(paqueteTravel);

        paqueteDePaquetes.decrementarCupo(LocalDate.of(2004,2,10), 2);

        verify(noche).decrementarCupo(LocalDate.of(2004,2,10), 2);
        verify(excursion).decrementarCupo(LocalDate.of(2004,2,10), 2);
        verify(paqueteTravel).decrementarCupo(LocalDate.of(2004,2,10), 2);
    }

	@Test
	void icrementarCupo(){
		paqueteDePaquetes.agregarItem(noche);
		paqueteDePaquetes.agregarItem(excursion);
        paqueteDePaquetes.agregarItem(paqueteTravel);

		paqueteDePaquetes.incrementarCupo(LocalDate.of(2004,2,10),2);

		verify(noche).incrementarCupo(LocalDate.of(2004,2,10),2);
		verify(excursion).incrementarCupo(LocalDate.of(2004,2,10),2);
		verify(paqueteTravel).incrementarCupo(LocalDate.of(2004,2,10),2);
	}


	@Test
    void contieneCategoria() {
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);

        when(noche.contieneCategoria(unaCategoria)).thenReturn(false);
        when(excursion.contieneCategoria(unaCategoria)).thenReturn(true);

        assertTrue(paqueteDePaquetes.contieneCategoria(unaCategoria));
        verify(noche).contieneCategoria(unaCategoria);
        verify(excursion).contieneCategoria(unaCategoria);
    }

    @Test
    void noContieneCategoria() {
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);
        paqueteDePaquetes.agregarItem(paqueteTravel);

        when(noche.contieneCategoria(unaCategoria)).thenReturn(false);
        when(excursion.contieneCategoria(unaCategoria)).thenReturn(false);
        when(paqueteTravel.contieneCategoria(unaCategoria)).thenReturn(false);

        assertFalse(paqueteDePaquetes.contieneCategoria(unaCategoria));
        verify(noche).contieneCategoria(unaCategoria);
        verify(excursion).contieneCategoria(unaCategoria);
        verify(paqueteTravel).contieneCategoria(unaCategoria);
    }


	@Test
    void recolectarServiciosIndividuales() {
        Set<Servicio> resultado = new HashSet<>();
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);
        paqueteDePaquetes.agregarItem(paqueteTravel);

        paqueteDePaquetes.recolectarServiciosIndividuales(resultado);

        verify(noche).recolectarServiciosIndividuales(resultado);
        verify(excursion).recolectarServiciosIndividuales(resultado);
        verify(paqueteTravel).recolectarServiciosIndividuales(resultado);
    }

	@Test
    void esDeProveedor() {
        Proveedor unProveedor = mock(Proveedor.class);

        assertFalse(paqueteDePaquetes.esDeProveedor(unProveedor));
    }

	@Test
    void agregarProveedoresA() {
        Set<Proveedor> proveedores = new HashSet<>();
        paqueteDePaquetes.agregarItem(noche);
        paqueteDePaquetes.agregarItem(excursion);
        paqueteDePaquetes.agregarItem(paqueteTravel);

        paqueteDePaquetes.agregarProveedoresA(proveedores);

        verify(noche).agregarProveedoresA(proveedores);
        verify(excursion).agregarProveedoresA(proveedores);
        verify(paqueteTravel).agregarProveedoresA(proveedores);
    }

}

