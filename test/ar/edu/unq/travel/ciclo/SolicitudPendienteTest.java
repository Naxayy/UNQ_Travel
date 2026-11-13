package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.*;
import ar.edu.unq.travel.traslados.TrasladoEstandar;

public class SolicitudPendienteTest {

	//se testea que los cambios de estado de la reserva sean validos

	ServicioIndividual estadiaCancun;
	TrasladoEstandar traslado;
	Reserva reserva;

	@BeforeEach
	void setUp() {
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.traslado = mock(TrasladoEstandar.class);

		this.reserva = new Reserva(traslado);
	}

	@Test
	void agregarServicio(){
		reserva.agregarServicio(estadiaCancun);
		assertTrue(reserva.elCarritoContiene(estadiaCancun));		
	}

	@Test
	void quitarServicio(){
		reserva.agregarServicio(estadiaCancun);
		assertTrue(reserva.elCarritoContiene(estadiaCancun));

		reserva.quitarServicio(estadiaCancun);
		assertFalse(reserva.elCarritoContiene(estadiaCancun));
	}

	@Test
	void seConfirmaLaReserva() {
		reserva.confirmar();
		assertEquals(Confirmada.class, reserva.getEstado().getClass());
		assertNotEquals(SolicitudPendiente.class, reserva.getEstado().getClass());
	}

	@Test
	void seCancelaLaReserva() {
		assertEquals(SolicitudPendiente.class, reserva.getEstado().getClass());
		reserva.cancelar();
		assertEquals(Cancelada.class, reserva.getEstado().getClass());
		assertNotEquals(SolicitudPendiente.class, reserva.getEstado().getClass());
	}

}