package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

import ar.edu.unq.travel.ciclo.*;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.servicios.*;
import ar.edu.unq.travel.traslados.Traslado;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;
	MetodoDePago unMetodoDePago;

	@BeforeEach
	public void setUp() {
		//DOCS
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
		this.unMetodoDePago = mock(MetodoDePago.class);
	
		//SUT
		this.reserva = new Reserva(unTraslado,unMetodoDePago);
	}

	@Test
	void testRealizarUnaOperacionEnUnEstadoInvalidoAlInicioDeLaReservaGeneraUnaExcepcion(){
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.finalizar() );
        assertEquals("no se puede finalizar la resera en este estado de la compra", error.getMessage());
	}

	@Test
	void testAgregaUnServicioAlCarritoAlInicioDeLaReservaNoGeneraUnaExcepcion(){
		assertDoesNotThrow(() -> this.reserva.agregarServicio(estadiaCancun));
	}

	@Test
	void testQuitarUnServicioAlCarritoAlInicioDeLaReservaNoGeneraUnaExcepcion(){
		reserva.agregarServicio(estadiaCancun);
		assertDoesNotThrow(() -> this.reserva.quitarServicio(estadiaCancun));
	}

	@Test
	void testConfirmarReservaAlInicioNoGeneraUnaExcepcion() {
		assertDoesNotThrow(() -> this.reserva.confirmar());
	}

	@Test
	void testRealizarChekinDespuesDeConfirmarLaReservaNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		assertDoesNotThrow(() -> this.reserva.enCheckin());
	}

	@Test
	void testReservaEnCursoDespuesDeEnCheckNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.enCurso());
	}

	@Test
	void testReservaFinalizadaDespuesDeEnCursoNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.finalizar());
	}

	@Test
	void testCancelarReservaDespuesDeCualquierEstadoNoGeneraUnaExcepcion() {
		assertDoesNotThrow(() -> this.reserva.cancelar());
		this.reserva.confirmar();
		assertDoesNotThrow(() -> this.reserva.cancelar());
		this.reserva.enCheckin();
		assertDoesNotThrow(() -> this.reserva.cancelar());
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.cancelar());
	}
}