package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class PaqueteTest {

	private ServicioIndividual noche;
	private ServicioIndividual excursion;
	private ServicioIndividual trasladoAH;
	
	private Paquete paqueteTravel;
	private Paquete paqueteDePaquetes;
	
	@BeforeEach
	void setUp() {
		//DOC
		this.noche = mock(ServicioIndividual.class);
		this.excursion = mock(ServicioIndividual.class);
		this.trasladoAH = mock(ServicioIndividual.class);
		this.paqueteTravel = mock(Paquete.class);
		
		//SUT
		this.paqueteDePaquetes = new Paquete ("nombre","descripcion",10, 1);
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

}

