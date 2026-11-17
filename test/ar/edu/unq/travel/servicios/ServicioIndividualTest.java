package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;
import ar.edu.unq.travel.categoria.Categoria;


public class ServicioIndividualTest {
	private AtributoDinamico ratingEstrellas;

	private ServicioIndividual noche;
	private ServicioIndividual excursion;
	private Map<LocalDate, Integer> map;
	private Proveedor proveedor;
	private Categoria unaCategoria;
	
	@BeforeEach
	void setUp() {
		this.ratingEstrellas = mock(AtributoDinamico.class);
		this.unaCategoria = mock(Categoria.class);
		this.map = new HashMap<LocalDate,Integer>();
		this.map.put(LocalDate.of(2004,2,10),1);
		this.proveedor = mock(Proveedor.class);

		//servicio individual con un de atributo por parametro
		this.noche = new ServicioIndividual("Noche de hotel central",15000.0, "descripcion",map, 1, unaCategoria,proveedor, ratingEstrellas);

		//servicio individual sin atributos
		this.excursion = new ServicioIndividual("Excursion guiada museo",4000.00,"Descripcion",map, 2, unaCategoria,proveedor );
	}

	@Test
	void agregarAtributo() {
		excursion.agregarAtributo(ratingEstrellas);
		assertTrue(excursion.getAtributosDinamicos().contains(ratingEstrellas));
	}

	@Test
	void esPrecioFinal() {
		assertEquals(15000.0 , noche.precioFinal());
	}

	@Test
	void esPrecioBase() {
		assertEquals(4000 , excursion.precioBase());
	}
	
	@Test
	void cambiarPrecio() {
		excursion.setPrecioBase(14000);
		
		assertNotEquals(4000 , excursion.precioBase());
		assertEquals(14000 , excursion.precioBase());
	}

	@Test
	void getCodigo(){
		assertEquals(2, excursion.getCodigo());
	}

	@Test
	void agregarProveedorASet(){
		Set<Proveedor> proveedores = new HashSet<>();
		noche.agregarProveedoresA(proveedores);

		assertTrue(proveedores.contains(proveedor));
		assertEquals(1, proveedores.size());
	}

}
