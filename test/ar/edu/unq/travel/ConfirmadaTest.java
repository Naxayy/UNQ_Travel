package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.ciclo.*;
import ar.edu.unq.travel.traslados.Traslado;

public class ConfirmadaTest {

    Reserva reserva;
	Traslado unTraslado;

    @BeforeEach
    void setUp() {
		this.unTraslado = mock(Traslado.class);
        this.reserva = new Reserva(unTraslado);
    }

	@Test
	void cofirmarCarritoYRealizarCheckin() {
		assertEquals(reserva.getEstado().getClass(), Pendiente.class);
		this.reserva.confirmar();
		assertEquals(reserva.getEstado().getClass(), Confirmada.class);
		reserva.enCheckin();
		assertEquals(reserva.getEstado().getClass(), EnCheckin.class);
	}

	@Test
	void cofirmarCarritoYCancelarlo() {
		assertEquals(reserva.getEstado().getClass(), Pendiente.class);
		this.reserva.confirmar();
		assertEquals(reserva.getEstado().getClass(), Confirmada.class);
		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelada.class);
	}
}
