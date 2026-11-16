package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.Carrito;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.servicios.ServicioIndividual;
import ar.edu.unq.travel.traslados.TrasladoEstandar;

public class SolicitudPendienteTest {

	//que el voucher reciba el monto correcto
	//el estado al ser cancelado da un monto para el voucher

	ServicioIndividual estadiaCancun;
	Reserva reserva;
	SolicitudPendiente estado;

	@BeforeEach
	void setUp() {
		this.reserva = mock(Reserva.class);
		this.estadiaCancun = mock(ServicioIndividual.class);

		this.estado = new SolicitudPendiente();
	}

	@Test
	void testAgregarServicioLeDelegaLaResponsabilidadAReserva(){
		this.estado.agregarServicio(this.reserva, this.estadiaCancun);
		verify(this.reserva).agregarAlCarrito(estadiaCancun);	
	}

	@Test
	void testQuitarServicioLeDelegaLaResponsabilidadAReserva(){
		this.estado.quitarServicio(this.reserva, this.estadiaCancun);
		verify(this.reserva).eliminarDelCarrito(this.estadiaCancun);	
	}

	@Test
	void testConfirmarLeDelegaLaResponsabilidadAReserva() {
    	this.estado.confirmar(this.reserva);

    	verify(this.reserva).cambiarAEstado(any(Confirmada.class));
    	verify(this.reserva).confirmarCarrito();
	}

	@Test
	void testCancelarLeDelegaLaResponsabilidadAReserva() {
    	this.estado.cancelada(this.reserva);

    	verify(this.reserva).cambiarAEstado(any(Cancelada.class));
	}
}