package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.ciclo.*;
import ar.edu.unq.travel.servicios.*;

public class SolicitudPendienteTest {

	
	Reserva reserva;
	ServicioIndividual estadiaCancun;

	@BeforeEach
	void setUp() {
		this.reserva = mock(Reserva.class);
		this.estadiaCancun = mock(ServicioIndividual.class);
	}

	void estadoInicial() {
		assertInstanceOf(Pendiente.class , reserva.getEstado());
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
	void estadoInicialACancelado() {
		assertInstanceOf(Pendiente.class , reserva.getEstado());
		reserva.cancelar();
		assertInstanceOf(Cancelada.class, reserva.getEstado());
	}


	@Test
	void invalidoDesdeInicial() {
		//todos los pedidos de estados posterior a confirmado son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCheckin();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizar();
		});
	}
}