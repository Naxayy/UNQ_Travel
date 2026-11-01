package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;

	@BeforeEach
	void setUp() {
		//DOC
		this.estadiaCancun = mock(ServicioIndividual.class);
	
		//SUT
		this.reserva = new Reserva();
	}
	
	@Test
	void estadoInicial() {
		assertInstanceOf(Pendiente.class , reserva.getEstado());
	}

	@Test
	void cambioDeEstado(){
		reserva.confirmar();
		assertInstanceOf(Confirmada.class, reserva.getEstado());
	}

	@Test
	void agregarServicio(){
		reserva.agregarServicio(estadiaCancun);
		assertTrue(reserva.getServicios().contains(estadiaCancun));		
	}

	@Test
	void quitarServicio(){
		reserva.agregarServicio(estadiaCancun);
		reserva.quitarServicio(estadiaCancun);
		assertFalse(reserva.getServicios().contains(estadiaCancun));
	}

	@Test
	void estadoConfirmada() {
		reserva.confirmar();
		assertInstanceOf(Confirmada.class, reserva.getEstado());
	}

	@Test
	void estadoEnConfirmadaError() {
		reserva.confirmar();
		reserva.agregarServicio(estadiaCancun);
		assertFalse(reserva.getServicios().contains(estadiaCancun));
		
	}

	@Test
	void estadoEnChekin() {
		reserva.enCheckin();
		assertInstanceOf(EnCheckin.class , reserva.getEstado());
	}

	@Test
	void estadoEnCurso() {
		reserva.enCurso();
		assertInstanceOf(EnCurso.class , reserva.getEstado());
	}

	@Test
	void estadoFinalizada() {
		reserva.finalizar();
		assertInstanceOf(Finalizada.class , reserva.getEstado());
	}

	@Test
	void estadoCancelada() {
		reserva.cancelar();
		assertInstanceOf(Cancelada.class , reserva.getEstado());
	}
}