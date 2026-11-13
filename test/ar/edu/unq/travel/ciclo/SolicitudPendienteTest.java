package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.*;
import ar.edu.unq.travel.traslados.TrasladoEstandar;

public class SolicitudPendienteTest {

	//que el voucher reciba el monto correcto
	//el estado al ser cancelado da un monto para el voucher

	ServicioIndividual estadiaCancun;
	Reserva reserva;
	SolicitudPendiente estado;

	@BeforeEach
	void setUp() {
		this.reserva = mock(Reserva.class)
		this.estadiaCancun = mock(ServicioIndividual.class);

		this.estado = new SolicitudPendiente();
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