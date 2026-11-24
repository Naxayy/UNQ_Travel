package ar.edu.unq.travel.servicios;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.PeriodoDeTiempo;
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
		when(this.unaCategoria.getNombre()).thenReturn("Alojamiento");

		this.map = new HashMap<LocalDate, Integer>();
		this.map.put(LocalDate.of(2004, 2, 10), 1);
		this.proveedor = mock(Proveedor.class);

		// Servicio individual con un atributo por parámetro
		this.noche = new ServicioIndividual("Noche de hotel central", 15000.0, "descripcion", map, 1, unaCategoria, proveedor, ratingEstrellas);

		// Servicio individual sin atributos (copia del mapa para no compartir estado mutable)
		this.excursion = new ServicioIndividual("Excursion guiada museo", 4000.00, "Descripcion", new HashMap<>(map), 2, unaCategoria, proveedor);
	}

	@Test
	void constructorConAtributoInicializaLaListaConEseAtributo() {
		assertEquals(1, noche.getAtributosDinamicos().size());
		assertTrue(noche.getAtributosDinamicos().contains(ratingEstrellas));
		assertTrue(excursion.getAtributosDinamicos().isEmpty());
	}

	@Test
	void agregarAtributo() {
		excursion.agregarAtributo(ratingEstrellas);
		assertTrue(excursion.getAtributosDinamicos().contains(ratingEstrellas));
	}

	@Test
	void esPrecioFinal() {
		assertEquals(15000.0, noche.precioFinal());
	}

	@Test
	void esPrecioBase() {
		assertEquals(4000, excursion.precioBase());
	}

	@Test
	void cambiarPrecio() {
		excursion.setPrecioBase(14000);

		assertNotEquals(4000, excursion.precioBase());
		assertEquals(14000, excursion.precioBase());
	}

	@Test
	void getCodigo() {
		assertEquals(2, excursion.getCodigo());
	}

	@Test
	void contieneCategoria() {
		Categoria mismaCategoria = mock(Categoria.class);
		when(mismaCategoria.getNombre()).thenReturn("Alojamiento");

		Categoria otraCategoria = mock(Categoria.class);
		when(otraCategoria.getNombre()).thenReturn("Transporte");

		assertTrue(noche.contieneCategoria(mismaCategoria));
		assertFalse(noche.contieneCategoria(otraCategoria));
	}

	@Test
	void hayDisponibilidadEnFecha() {
		LocalDate fechaConCupo = LocalDate.of(2004, 2, 10);
		LocalDate fechaSinCupo = LocalDate.of(2004, 2, 11);

		assertTrue(noche.hayDisponibilidadEnFecha(fechaConCupo, 1));
		assertFalse(noche.hayDisponibilidadEnFecha(fechaConCupo, 2));
		assertFalse(noche.hayDisponibilidadEnFecha(fechaSinCupo, 1));
	}

	@Test
	void hayDisponibilidadEnPeriodo() {
		noche.setCuposDisponibles(LocalDate.of(2004, 2, 10), 3);
		noche.setCuposDisponibles(LocalDate.of(2004, 2, 11), 2);
		noche.setCuposDisponibles(LocalDate.of(2004, 2, 12), 4);

		PeriodoDeTiempo periodoValido = new PeriodoDeTiempo(LocalDate.of(2004, 2, 10), LocalDate.of(2004, 2, 12));
		PeriodoDeTiempo periodoSinCupoEnUnDia = new PeriodoDeTiempo(LocalDate.of(2004, 2, 10), LocalDate.of(2004, 2, 13));

		assertTrue(noche.hayDisponibilidad(periodoValido, 2));
		assertFalse(noche.hayDisponibilidad(periodoValido, 3));
		assertFalse(noche.hayDisponibilidad(periodoSinCupoEnUnDia, 1));
	}

	@Test
	void decrementarCupo() {
		LocalDate fecha = LocalDate.of(2004, 2, 10);
		noche.setCuposDisponibles(fecha, 5);

		noche.decrementarCupo(fecha, 3);

		assertEquals(2, noche.getCuposDisponibles().get(fecha));
	}

	@Test
	void decrementarCupoSinDisponibilidadLanzaExcepcion() {
		LocalDate fecha = LocalDate.of(2004, 2, 10);

		IllegalStateException error = assertThrows(
			IllegalStateException.class,
			() -> noche.decrementarCupo(fecha, 2)
		);

		assertEquals("No hay cupos suficientes", error.getMessage());
	}

	@Test
	void incrementarCupo() {
		LocalDate fechaExistente = LocalDate.of(2004, 2, 10);
		LocalDate fechaNueva = LocalDate.of(2004, 2, 15);

		noche.incrementarCupo(fechaExistente, 4);
		noche.incrementarCupo(fechaNueva, 2);

		assertEquals(5, noche.getCuposDisponibles().get(fechaExistente));
		assertEquals(2, noche.getCuposDisponibles().get(fechaNueva));
	}

	@Test
	void recolectarServiciosIndividuales() {
		Set<Servicio> resultado = new HashSet<>();

		noche.recolectarServiciosIndividuales(resultado);

		assertEquals(Set.of(noche), resultado);
	}

	@Test
	void agregarProveedorASet() {
		Set<Proveedor> proveedores = new HashSet<>();
		noche.agregarProveedoresA(proveedores);

		assertTrue(proveedores.contains(proveedor));
		assertEquals(1, proveedores.size());
	}

	@Test
	void esDeProveedor() {
		Proveedor otroProveedor = mock(Proveedor.class);

		assertTrue(noche.esDeProveedor(proveedor));
		assertFalse(noche.esDeProveedor(otroProveedor));
	}
}