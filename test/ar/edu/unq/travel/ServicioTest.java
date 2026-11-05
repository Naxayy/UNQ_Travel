package ar.edu.unq.travel;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.servicios.Paquete;
import ar.edu.unq.travel.servicios.ServicioIndividual;

class ServicioTest {
	private ServicioIndividual noche;
	private Paquete paqueteTravel;
	private Paquete paqueteDePaquetes;
	
	@BeforeEach
	void setUp() {
		this.noche = new ServicioIndividual("Noche de hotel central",15000.00, "descripcion", 1);
		this.paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",10);
		this.paqueteDePaquetes = new Paquete ("nombre","descripcion",10);
	}

	@Test 
	void precioBaseDeNoche(){
		assertEquals(15000.00, noche.precioBase());
	}

	@Test 
	void precioBaseDePaqueteTravel(){
		paqueteTravel.agregarItem(noche);

		assertNotEquals(14000.00, paqueteTravel.precioBase());
	}

	@Test 
	void precioFinalDePaqueteDePaquetes(){
		paqueteTravel.agregarItem(noche);
		paqueteDePaquetes.agregarItem(paqueteTravel);
		paqueteDePaquetes.agregarItem(noche);

		assertEquals(27000.0, paqueteDePaquetes.precioFinal());
	}

	@Test 
	void noEsNombreDelServicio(){
		assertNotEquals("paqueteDePaquetes", noche.getNombre());
	}

	@Test 
	void descripcionDelServicio(){
		assertNotEquals("descripcion", paqueteTravel.getDescripcion());
	}

	@Test 
	void cambiarNombreDelServicio(){
		noche.setNombre("Dia de hotel central");
		assertNotEquals("Dia de hotel central", noche.getNombre());
	}

	@Test 
	void cambiarDescripcionDelServicio(){
		paqueteTravel.setDescripcion("muy completo");
		assertNotEquals("muy completo", paqueteTravel.getDescripcion());
	}



}
