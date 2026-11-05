package ar.edu.unq.travel;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioTest {
	private ServicioIndividual noche;
	private Paquete paqueteTravel;
	
	@BeforeEach
	void setUp() {

		//al ser clase abstracta instancio sus dos clases hijas y no repito test.
		this.noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion", 1);
		this.paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",10, 2);
	}

	@Test 
	void noEsNombreDelServicio(){
		assertNotEquals("paqueteDePaquetes", noche.getNombre());
	}

	@Test
	void esCodigoDelServicio(){
		assertEquals(2, paqueteTravel.getCodigo());
	}

	@Test 
	void descripcionDelServicio(){
		assertEquals("descripcion", paqueteTravel.getDescripcion());
	}

	@Test 
	void cambiarNombreDelServicio(){
		noche.setNombre("Dia de hotel central");
		assertNotEquals("Noche de hotel central", noche.getNombre());
		assertEquals("Dia de hotel central", noche.getNombre());
	}

	@Test 
	void cambiarDescripcionDelServicio(){
		paqueteTravel.setDescripcion("muy completo");
		assertEquals("muy completo", paqueteTravel.getDescripcion());
	}



}
