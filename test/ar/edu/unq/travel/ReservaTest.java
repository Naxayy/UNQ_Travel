package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.ciclo.*;
import ar.edu.unq.travel.servicios.*;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.traslados.Traslado; 

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;

	@BeforeEach
	void setUp() {
		//DOC
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
	
		//SUT
		this.reserva = new Reserva(unTraslado);
	}
	
	@Test
	void testElEstadoInicialdeReservaEsPendienteYNoOtro() {
		assertEquals(reserva.getEstado().getClass(), SolicitudPendiente.class);
		assertNotEquals(reserva.getEstado().getClass(), Confirmada.class);
		assertNotEquals(reserva.getEstado().getClass(), EnCheckin.class);
		assertNotEquals(reserva.getEstado().getClass(), EnCurso.class);
		assertNotEquals(reserva.getEstado().getClass(), Finalizada.class);
		assertNotEquals(reserva.getEstado().getClass(), Cancelada.class);
	}

	@Test
	void testRealizarUnaOperacionEnUnEstadoInvalidoGeneraUnaExcepcion(){
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> reserva.finalizar() );
        assertEquals("no se puede finalizar la resera en este estado de la compra", error.getMessage());
	}

	@Test
	void testAgregaUnServicioAlCarritoYElEstadoSigueSiendoPendiente(){
		reserva.agregarServicio(estadiaCancun);
		assertEquals(reserva.getEstado().getClass(), SolicitudPendiente.class);
	}

	@Test
	void testQuitarUnServicioAlCarritoYElEstadoSigueSiendoPendiente(){
		reserva.agregarServicio(estadiaCancun);
		reserva.quitarServicio(estadiaCancun);
		assertEquals(reserva.getEstado().getClass(), SolicitudPendiente.class);
	}

	@Test
	void testConfirmarReservaYElEstadoCambiaAConfirmada() {
		reserva.confirmar();
		assertEquals(reserva.getEstado().getClass(), Confirmada.class);
	}

	@Test
	void testRealizarChekinDeReservaYElEstadoCambiaACheckIn() {
		reserva.confirmar();
		reserva.enCheckin();
		assertEquals(reserva.getEstado().getClass(), EnCheckin.class);
	}

	@Test
	void testReservaEnCursoYElEstadoCambiaAEnCurso() {
		reserva.confirmar();
		reserva.enCheckin();
		reserva.enCurso();
		assertEquals(reserva.getEstado().getClass(), EnCurso.class);
	}

	@Test
	void testReservaFinalizadaYElEstadoCambiaAFinalizada() {
		reserva.confirmar();
		reserva.enCheckin();
		reserva.enCurso();
		reserva.finalizar();
		assertEquals(reserva.getEstado().getClass(), Finalizada.class);
	}

	@Test
	void testCancelarReservaYElEstadoCambiaACancelada() {
		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelada.class);
	}
}