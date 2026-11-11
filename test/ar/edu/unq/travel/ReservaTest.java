package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

import ar.edu.unq.travel.ciclo.*;
import ar.edu.unq.travel.servicios.*;
import ar.edu.unq.travel.traslados.Traslado;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;

	@BeforeEach
	public void setUp() {
		//DOCS
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
	
		//SUT
		this.reserva = new Reserva(unTraslado);
	}
	
	@Test
	void estadoInicial() {
		assertInstanceOf(Pendiente.class, reserva.getEstado());
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
		assertThrows(IllegalStateException.class, () -> {
			reserva.agregarServicio(estadiaCancun);
		});
		
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