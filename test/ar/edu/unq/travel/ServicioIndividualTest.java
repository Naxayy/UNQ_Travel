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
		noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion");
		excursion = new ServicioIndividual("Excursion guiada museo " , 4000.00,"Descripcion");
		trasladoAH = new ServicioIndividual("Traslado Aeropuerto-Hotel", 2000.00 ,"Descripcion");
	}

	@Test
	void valoresDeServicioSimple() {
		assertEquals(15000 , noche.precioFinal());
		assertEquals(4000 , excursion.precioFinal());
		assertEquals(2000, trasladoAH.precioFinal());
	}

}
