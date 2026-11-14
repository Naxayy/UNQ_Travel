package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.Carrito;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.servicios.ServicioIndividual;
import ar.edu.unq.travel.traslados.TrasladoEstandar;

public class SolicitudPendienteTest {

	ServicioIndividual estadiaCancun;
	TrasladoEstandar traslado;
	Reserva reserva;

	@BeforeEach
	void setUp() {
		MetodoDePago pagoDummy = mock(MetodoDePago.class);
		int cantPasajerosDummy = 0; //al ser un int no se puede mockear como los otros pero le damos un valor random ya que no es usado en el test
		this.reserva = new Reserva(traslado, pagoDummy, cantPasajerosDummy);
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.traslado = mock(TrasladoEstandar.class);
	}

	@Test
	void agregarServicio(){
		Carrito carrito = reserva.getCarrito();
		reserva.agregarServicio(estadiaCancun);
		assertTrue(carrito.contiene(estadiaCancun));		
	}

	@Test
	void quitarServicio(){
		Carrito carrito = reserva.getCarrito();
		reserva.agregarServicio(estadiaCancun);
		assertTrue(carrito.contiene(estadiaCancun));

		reserva.quitarServicio(estadiaCancun);
		assertFalse(carrito.contiene(estadiaCancun));
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