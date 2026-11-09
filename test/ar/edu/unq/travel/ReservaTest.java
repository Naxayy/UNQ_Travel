package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.ciclo.Cancelada;
import ar.edu.unq.travel.ciclo.Confirmada;
import ar.edu.unq.travel.ciclo.EnCheckin; 
import ar.edu.unq.travel.ciclo.EnCurso;
import ar.edu.unq.travel.ciclo.Finalizada;
import ar.edu.unq.travel.servicios.ServicioIndividual;
import ar.edu.unq.travel.traslados.Traslado;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;

	@BeforeEach
	public void setUp() {
		//DOC
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
	
		//SUT
		this.reserva = new Reserva(unTraslado);
	}
	
	@Test
	void estadoInicial() {
		//assertInstanceOf(Pendiente.class , reserva.getEstado());
		verify(reserva).getEstado(new Pendiente());
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