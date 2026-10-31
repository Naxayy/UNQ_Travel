package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AtributoDinamicoTest {
	
	variables 
	
	@BeforeEach
	void setUp() {
		//DOC
		
		//SUT
	}
	
	@Test
	void n() {
		paqueteTravel.agregarItem(noche);
		paqueteTravel.agregarItem(excursion);
		paqueteTravel.agregarItem(trasladoAH);
		
		assertEquals(18900, paqueteTravel.precioFinal());
	}
}
