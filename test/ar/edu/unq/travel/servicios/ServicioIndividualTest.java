package ar.edu.unq.travel.servicios;

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

	private Categoria unaCategoria;
	
	@BeforeEach
	void setUp() {
		this.ratingEstrellas = mock(AtributoDinamico.class);
		this.unaCategoria = mock(Categoria.class);

		//servicio individual con un de atributo por parametro
		this.noche = new ServicioIndividual("Noche de hotel central", "descripcion", 1, unaCategoria,15000.00);// this.ratingEstrellas);

		//servicio individual sin atributos
		this.excursion = new ServicioIndividual("Excursion guiada museo","Descripcion", 2, unaCategoria, 4000.00);
	}

	@Test
	void agregarAtributo() {
		excursion.agregarAtributo(ratingEstrellas);
		assertTrue(excursion.getAtributosDinamicos().contains(ratingEstrellas));
	}

	@Test
	void esPrecioFinal() {
		assertEquals(15000 , noche.precioFinal());
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

}
