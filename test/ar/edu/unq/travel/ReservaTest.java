package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.plataformaTransporte.Direccion;
import ar.edu.unq.travel.servicios.ServicioIndividual;
import ar.edu.unq.travel.traslados.Traslado;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;
	MetodoDePago unMetodoDePago;
	Carrito carrito;
	Estado estado;
	Direccion direccion;

	@BeforeEach
	public void setUp() {
		//DOCS
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
		this.unMetodoDePago = mock(MetodoDePago.class);
		this.direccion = mock(Direccion.class);

		this.carrito = mock(Carrito.class);
		this.estado = mock(Estado.class);
	
		//SUT
		this.reserva = new Reserva(this.unTraslado, this.unMetodoDePago, this.carrito);
	}

	@Test
	void testAgregaUnServicioEnUnEstadoQueNoSeaPendienteGeneraUnaExcepcion(){
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
	void testUnaReservaAgregarUnServicioYElServicioYaNoApareceEnElCarrito() {
		when(this.carrito.contieneElServicio(estadiaCancun)).thenReturn(true);

    	this.reserva.agregarServicio(this.estadiaCancun);
		assertTrue(this.reserva.getCarrito().contieneElServicio(estadiaCancun));
	}

	@Test 
	void testQuitarUnServicioEnOtroEstadoQueNoSeaPendienteGeneraUnaExcepcion(){
		this.reserva.agregarServicio(estadiaCancun);
		this.reserva.confirmar();
		IllegalStateException error = assertThrows(IllegalStateException.class, ()-> this.reserva.quitarServicio(estadiaCancun));
		assertEquals("solo se pueden quitar servicios al iniciar la reserva", error.getMessage());
	}

	@Test
	void testQuitarUnServicioDelCarritoDeLaReservaNoGeneraUnaExcepcion(){
		reserva.agregarServicio(estadiaCancun);
		assertDoesNotThrow(() -> this.reserva.quitarServicio(estadiaCancun));
	}

	@Test
	void testQuitarUnServicioAlCarritoDelegaLaResonsabilidadAOtro(){
    	this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun); 
		this.reserva.quitarServicio(this.estadiaCancun); 
		verify(this.estado).quitarServicio(this.reserva, this.estadiaCancun);
	}

	@Test
	void testUnaReservaQuitaUnServicioYElServicioYaNoApareceEnElCarrito() {
		when(this.carrito.contieneElServicio(estadiaCancun)).thenReturn(false);

    	this.reserva.agregarServicio(this.estadiaCancun);
		this.reserva.quitarServicio(this.estadiaCancun);
		
		assertFalse(this.reserva.getCarrito().contieneElServicio(estadiaCancun));
	}

	@Test
	void testconfirmarUnaReservaEnUnEstadoQueNoSeaPendienteGeneraUnaExcepcion(){
		this.reserva.agregarServicio(estadiaCancun);
		this.reserva.confirmar();
		this.reserva.enCheckin();
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.confirmar() );
        assertEquals("no se puede confirmar la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testConfirmarLaReservaDelegaLaResonsabilidadAOtro(){
    	this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun); 
		this.reserva.confirmar();
		verify(this.estado).confirmar(this.reserva);
	}

	@Test
	void testConfirmarLaReservaDespuesDelEstadoInicialNoGeneraUnaExcepcion() {
		assertDoesNotThrow(() -> this.reserva.confirmar());
	}


	@Test
	void testHacerCheckinDeLaReservaEnUnEstadoQueNoSeaConfirmadoGeneraUnaExcepcion(){
		this.reserva.agregarServicio(estadiaCancun);
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.enCheckin() );
        assertEquals("no se puede realizar checkin de la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testRealizarChekinDespuesDeConfirmarLaReservaNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		assertDoesNotThrow(() -> this.reserva.enCheckin());
	} 

	@Test
	void testRealizarChekiDeLaReservaDelegaLaResonsabilidadAOtro(){
    	this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun); 
		this.reserva.confirmar();
		this.reserva.enCheckin();
		verify(this.estado).enCheckin(this.reserva);
	}

	@Test
	void testReservaEnCursoEnUnEstadoInvalidoGeneraUnaExcepcion(){
		this.reserva.agregarServicio(estadiaCancun);
		this.reserva.confirmar();
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.enCurso() );
        assertEquals("no se puede poner en curso a la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testReservaEnCursoDelegaLaResonsabilidadAOtro(){
    	this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun); 
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		verify(this.estado).enCurso(this.reserva);
	}

	void testReservaEnCursoDespuesDeEnCheckNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.enCurso());
	}

	@Test
	void testFinaliarUnaReservaEnUnEstadoInvalidoGeneraUnaExcepcion(){
		this.reserva.agregarServicio(estadiaCancun);
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.finalizar() );
        assertEquals("no se puede finalizar la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testFinaliarUnaReservaDelegaLaResonsabilidadAOtro(){
    	this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun); 
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		this.reserva.finalizar();
		verify(this.estado).finalizada(this.reserva);
	}

	@Test
	void testReservaFinalizadaDespuesDeEnCursoNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		assertDoesNotThrow(() -> this.reserva.finalizar());
	}


	@Test
	void testCancelarUnaReservaEnUnEstadoInvalidoGeneraUnaExcepcion(){
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		this.reserva.finalizar();
		IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> this.reserva.cancelar() );
        assertEquals("no se puede cancelar la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testCancelarUnaReservaDelegaLaResonsabilidadAOtro(){
		this.reserva.cambiarAEstado(this.estado);
    	this.reserva.cancelar();
		verify(this.estado).cancelada(this.reserva);
	}

	@Test
	void testCancelarCarritoDelegaEnCarrito() {
    	reserva.cancelarCarrito();

    	verify(this.carrito).cancelar();
	}

	@Test
	void testCostoDeServicioDelegaEnCarrito() {
		when(this.carrito.costoDeServicio()).thenReturn(1500.0);

		assertEquals(1500.0, this.reserva.costoDeServicio());

		verify(this.carrito).costoDeServicio();
	}

	@Test
	void testTasasAdministrativasDelegaEnCarrito() {
    	when(this.carrito.tasasAdministrativas()).thenReturn(200.0);

    	assertEquals(200.0, this.reserva.tasasAdministrativas());

	    verify(this.carrito).tasasAdministrativas();
	}

	@Test
	void testCostoDeServicioConTasasSumaLosValoresDelCarrito() {
		when(carrito.costoDeServicio()).thenReturn(1500.0);
		when(carrito.tasasAdministrativas()).thenReturn(200.0);

		assertEquals(1700.0, reserva.costoDeServicioConTasas());
	}

	@Test
	void testAgegraUnPasajeroALaReserva() {
		this.reserva.cambiarLaCantidadDePasajeros(2);

		assertEquals(this.reserva.pasajeros(), 2);
	}

	@Test
	void testDireccionDelViaje() {
		this.reserva.cambiarDireccionDestino(this.direccion);

		assertEquals(this.reserva.direccionDestino(), this.direccion);
	}
}