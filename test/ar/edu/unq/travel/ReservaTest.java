package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void setUp() {
		//DOC
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
		this.unMetodoDePago = mock(MetodoDePago.class);
	
		//SUT
		this.reserva = new Reserva(unTraslado,unMetodoDePago);
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