package ar.edu.unq.travel.servicios;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
	private Map<LocalDate, Integer> map;
	private LocalDate fecha = LocalDate.of(2004,02,01);
	private Proveedor proveedor;
	
	@BeforeEach
	void setUp() {
		this.unaCategoria = mock(Categoria.class);
		this.map = new HashMap<LocalDate,Integer>();
		this.map.put(LocalDate.of(2004,2,10),1);
		this.proveedor = mock(Proveedor.class);
		

		//al ser clase abstracta instancio sus dos clases hijas 
		//testeo los metodos heredados y no repeto codigo ni test
//String nombre, double precioBase, String descripcion, Map<LocalDate, Integer> cuposDisponibles, int codigo, Categoria categoria, Proveedor proveedor
		this.noche = new ServicioIndividual("Noche de hotel central", 10,"descripcion", map,1, unaCategoria,proveedor);
		//String nombre, String descripcion, Map<LocalDate, Integer> cuposDisponibles, int descuento, Categoria categoria
		this.paqueteTravel = new Paquete ("Pack escapada urbana","descripcion",map,10, unaCategoria);
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

	@Test
	void getCuposDisponibles(){
		Map<LocalDate, Integer> esperado = new HashMap<>();
        esperado.put(LocalDate.of(2004, 2, 10), 1);
		
		assertEquals(paqueteTravel.getCuposDisponibles(), esperado);
	}

	@Test
	void setCuposDisponibles(){
		Map<LocalDate, Integer> esperado = new HashMap<>();
        esperado.put(LocalDate.of(2004, 2, 10), 1);
		esperado.put(LocalDate.of(2000,11,20),2);
		map.put(LocalDate.of(2000,11,20),2);
		assertEquals(esperado, map);
	}

	@Test
	void confirmar(){
		
	}


	@Test
	void cancelar(){

	}
	@Test
	void cuposDisponiblesEnPeriodo(){

	}


}
