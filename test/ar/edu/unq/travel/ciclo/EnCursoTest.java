package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;

public class EnCursoTest {
    Reserva reserva;
	EnCurso estado;

    @BeforeEach
    void setUp() {
        this.reserva = mock(Reserva.class);

		this.estado = new EnCurso();
    }

	@Test
	void testPasarAFinalizadoLeDelegaLaResponsabilidadAReserva() {
    	this.estado.finalizada(this.reserva);

    	verify(this.reserva).cambiarAEstado(any(Finalizada.class));
	}

	@Test
	void testCancelarLeDelegaLaResponsabilidadAReserva() {
    	this.estado.cancelada(this.reserva);

    	verify(this.reserva).cambiarAEstado(any(Cancelada.class));
	}

    @Test
	void testMontoVoucherDa() {
        when(this.reserva.costoDeServicio()).thenReturn(200.0);
    	
        assertEquals(200, this.estado.montoVoucher(this.reserva));
	}
}
