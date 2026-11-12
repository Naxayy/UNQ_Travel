package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.servicios.ServicioIndividual;
import ar.edu.unq.travel.traslados.Traslado;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;
	MetodoDePago unMetodoDePago;
	Carrito carrito;
	Estado estado;

	@BeforeEach
	public void setUp() {
		//DOCS
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
		this.unMetodoDePago = mock(MetodoDePago.class);

		this.carrito = mock(Carrito.class);
		this.estado = mock(Estado.class);
	
		//SUT
		this.reserva = new Reserva(this.unTraslado, this.unMetodoDePago);
	}

	@Test
	void testAgregaUnServicioEnUnEstadoInvalidoGeneraUnaExcepcion(){
		reserva.agregarServicio(estadiaCancun);
		reserva.confirmar();
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.agregarServicio(estadiaCancun) );
        assertEquals("solo se pueden agregar servicios al iniciar la reserva", error.getMessage());
	}

	@Test
	void testAgregaUnServicioAlCarritoNoGeneraUnaExcepcion(){
		assertDoesNotThrow(() -> this.reserva.agregarServicio(estadiaCancun));
	}

	@Test
	void testAgregaUnServicioAlCarritoDelegaLaResonsabilidadAOtro(){
    	this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun); 
		verify(this.estado).agregarServicio(this.reserva, this.estadiaCancun);
	}

	@Test
	void testUnaReservaPuedeAgregarUnServicioAlCarrito() {
    	this.reserva.agregarServicio(this.estadiaCancun);
		assertTrue(this.reserva.getCarrito().getServicios().contains(this.estadiaCancun));
	}

	//

	@Test
	void testQuitarUnServicioAlCarritoAlInicioDeLaReservaNoGeneraUnaExcepcion(){
		reserva.agregarServicio(estadiaCancun);
		assertDoesNotThrow(() -> this.reserva.quitarServicio(estadiaCancun));
	}

	@Test
	void testConfirmarReservaAlInicioNoGeneraUnaExcepcion() {
		assertDoesNotThrow(() -> this.reserva.confirmar());
	}

	@Test
	void testRealizarChekinDespuesDeConfirmarLaReservaNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		assertDoesNotThrow(() -> this.reserva.enCheckin());
	}

	@Test
	void estadoEnConfirmadaError() {
		reserva.confirmar();	
		assertThrows(IllegalStateException.class, () -> {
			reserva.agregarServicio(estadiaCancun);
		});
	}

	void testReservaEnCursoDespuesDeEnCheckNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.enCurso());
	}

	@Test
	void testReservaFinalizadaDespuesDeEnCursoNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.finalizar());
	}

	@Test
	void testCancelarReservaDespuesDeCualquierEstadoNoGeneraUnaExcepcion() {
		assertDoesNotThrow(() -> this.reserva.cancelar());
		this.reserva.confirmar();
		assertDoesNotThrow(() -> this.reserva.cancelar());
		this.reserva.enCheckin();
		assertDoesNotThrow(() -> this.reserva.cancelar());
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.cancelar());
	}
}