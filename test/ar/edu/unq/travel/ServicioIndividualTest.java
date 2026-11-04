package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class ServicioIndividualTest {
	private AtributoDinamico ratingEstrellas;

	private ServicioIndividual noche;
	private ServicioIndividual excursion;
	private ServicioIndividual trasladoAH;
	
	@BeforeEach
	void setUp() {
		this.ratingEstrellas = mock(AtributoDinamico.class);

		//servicio individual con un de atributo por parametro
		this.noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion", 1, this.ratingEstrellas);

		//servicio individual sin atributos
		this.excursion = new ServicioIndividual("Excursion guiada museo" , 4000.00,"Descripcion", 2);
	}

	@Test
	void agregarAtributo() {
		excursion.agregarAtributo(ratingEstrellas);
		
	}
	
	@Test
	void testNombres() {
		assertEquals("Noche de hotel central", noche.getNombre());
		assertEquals("Excursion guiada museo", excursion.getNombre());
		assertEquals("Traslado Aeropuerto-Hotel", trasladoAH.getNombre());
	}

	@Test
	void testPrecioFinal() {
		assertEquals(15000 , noche.precioFinal());
		assertEquals(4000 , excursion.precioFinal());
		assertEquals(2000, trasladoAH.precioFinal());
	}

}
