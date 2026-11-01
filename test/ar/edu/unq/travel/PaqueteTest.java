package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		
		//SUT
		this.paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",10);
		this.paqueteDePaquetes = new Paquete ("nombre","descripcion",10);
	}
	
	@Test
	void valorDePaquete() {
		paqueteTravel.agregarItem(noche);
		paqueteTravel.agregarItem(excursion);
		paqueteTravel.agregarItem(trasladoAH);
		
		assertEquals(18900, paqueteTravel.precioFinal());
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

