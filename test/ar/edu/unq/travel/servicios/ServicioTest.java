package ar.edu.unq.travel.servicios;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.categoria.Categoria;

class ServicioTest {
	
	private ServicioIndividual noche;
	private Paquete paqueteTravel;
	private Categoria unaCategoria;
	
	@BeforeEach
	void setUp() {
		this.unaCategoria = mock(Categoria.class);
		
		//al ser clase abstracta instancio sus dos clases hijas 
		//testeo los metodos heredados y no repeto codigo ni test
		this.noche = new ServicioIndividual("Noche de hotel central", "descripcion", 1, unaCategoria,15000.00);
		this.paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",10, unaCategoria);
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
