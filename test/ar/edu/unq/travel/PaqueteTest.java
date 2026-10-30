package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

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
		noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion", "id");
		excursion = new ServicioIndividual("Excursion guiada museo " , 4000.00,"Descripcion", "id");
		trasladoAH = new ServicioIndividual("Traslado Aeropuerto-Hotel", 2000.00 ,"Descripcion", "id");
		paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",10) ;
		paqueteDePaquetes = new Paquete ("nombre","descripcion",10);
	}

	@Test
	void valoresDeItemSimple() {
		assertEquals(15000 , noche.precioFinal());
		assertEquals(4000 , excursion.precioFinal());
		assertEquals(2000, trasladoAH.precioFinal());
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

