package ar.edu.unq.travel;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioTest {
	private ServicioIndividual noche;
	private ServicioIndividual excursion;
	private ServicioIndividual trasladoAH;
	private Paquete paqueteTravel;
	private Paquete paqueteDePaquetes;
	
	@BeforeEach
	void setUp() {
		//DOC
		noche = mock(ServicioIndividual.class);
		excursion = mock(ServicioIndividual.class);
		trasladoAH = mock(ServicioIndividual.class);
		paqueteTravel = mock(Paquete.class);
		
		//SUT
		paqueteDePaquetes = new Paquete ("nombre","descripcion",10);
	}

	
	@Test
	void valorDePaqueteDePaquetes() {
		paqueteTravel.agregarItem(noche);
		paqueteTravel.agregarItem(excursion);
		paqueteTravel.agregarItem(trasladoAH);
		paqueteDePaquetes.agregarItem(noche);
		paqueteDePaquetes.agregarItem(paqueteTravel);
		
		assertEquals(30510, paqueteDePaquetes.precioFinal());
		
	}
	

}
