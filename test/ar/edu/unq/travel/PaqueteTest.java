package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.servicios.Paquete;
import ar.edu.unq.travel.servicios.ServicioIndividual;

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
		this.paqueteDePaquetes = new Paquete ("nombre","descripcion",10);
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

	//holaa

}

