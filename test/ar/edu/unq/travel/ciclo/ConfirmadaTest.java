package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.traslados.Traslado;

public class ConfirmadaTest {

    Reserva reserva;
	Traslado unTraslado;
	MetodoDePago medioDePagoDummy;

    @BeforeEach
    void setUp() {
		this.unTraslado = mock(Traslado.class);
		medioDePagoDummy = mock(MetodoDePago.class);
        this.reserva = new Reserva(unTraslado,medioDePagoDummy, 0);
    }

	@Test
	void cofirmarCarritoYRealizarCheckin() {
		assertEquals(reserva.getEstado().getClass(), SolicitudPendiente.class);
		this.reserva.confirmar();
		assertEquals(reserva.getEstado().getClass(), Confirmada.class);
		reserva.enCheckin();
		assertEquals(reserva.getEstado().getClass(), EnCheckin.class);
	}

	@Test
	void cofirmarCarritoYCancelarlo() {
		assertEquals(reserva.getEstado().getClass(), SolicitudPendiente.class);
		this.reserva.confirmar();
		assertEquals(reserva.getEstado().getClass(), Confirmada.class);
		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelada.class);
	}
}
