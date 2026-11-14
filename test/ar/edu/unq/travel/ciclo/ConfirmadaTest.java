package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.traslados.Traslado;

public class ConfirmadaTest {

    Reserva reserva;
	Confirmada estado;

    @BeforeEach
    void setUp() {
        this.reserva = mock(Reserva.class);

		this.estado = new Confirmada();
    }

	@Test
	void testRealizarCheckinLeDelegaLaResponsabilidadAReserva() {
    	this.estado.enCheckin(this.reserva);

    	verify(this.reserva).cambiarAEstado(any(EnCheckin.class));
	}

	@Test
	void testCancelarLeDelegaLaResponsabilidadAReserva() {
    	this.estado.cancelada(this.reserva);

    	verify(this.reserva).cambiarAEstado(any(Cancelada.class));
    	verify(this.reserva).cancelarCarrito();
	}

	@Test
	void testConfirmadaTiene0DeMontoVaucher() {
		assertEquals(0.0, this.estado.montoVoucher(this.reserva));
	}
}
