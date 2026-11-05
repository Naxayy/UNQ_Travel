package ar.edu.unq.travel;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.servicios.Paquete;
import ar.edu.unq.travel.servicios.ServicioIndividual;

class ServicioTest {
	private ServicioIndividual noche;
	private Paquete paqueteTravel;
	
	@BeforeEach
	void setUp() {

		//al ser clase abstracta instancio sus dos clases hijas.
		this.noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion", 1);
		this.paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",10);
	}

	@Test 
	void noEsNombreDelServicio(){
		assertNotEquals("paqueteDePaquetes", noche.getNombre());
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
