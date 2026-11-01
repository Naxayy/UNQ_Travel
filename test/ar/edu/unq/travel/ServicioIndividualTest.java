package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioIndividualTest {
	private ServicioIndividual noche;
	private ServicioIndividual excursion;
	private ServicioIndividual trasladoAH;
	
	@BeforeEach
	void setUp() {
		this.noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion", 1);
		this.excursion = new ServicioIndividual("Excursion guiada muse " , 4000.00,"Descripcion", 2);
		this.trasladoAH = new ServicioIndividual("Traslado Aeropuerto-Hotel", 2000.00 ,"Descripcion", 3);
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
