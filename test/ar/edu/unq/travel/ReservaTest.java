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
	void confirmarReserva() {
		reserva.confirmar();
		assertEquals(reserva.getEstado().getClass(), Confirmada.class);
	}

	@Test
	void estadoEnChekin() {
		reserva.confirmar();
		reserva.enCheckin();
		assertInstanceOf(EnCheckin.class , reserva.getEstado());
	}

	@Test
	void estadoEnCurso() {
		reserva.confirmar();
		reserva.enCheckin();
		reserva.enCurso();
		assertInstanceOf(EnCurso.class , reserva.getEstado());
	}

	@Test
	void estadoFinalizada() {
		reserva.confirmar();
		reserva.enCheckin();
		reserva.enCurso();
		reserva.finalizar();
		assertInstanceOf(Finalizada.class , reserva.getEstado());
	}

	@Test
	void estadoCancelada() {
		reserva.cancelar();
		assertInstanceOf(Cancelada.class , reserva.getEstado());
	}
}