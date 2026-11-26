package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.ciclo.Confirmada;
import ar.edu.unq.travel.ciclo.EnCheckin;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.ciclo.SolicitudPendiente;
import ar.edu.unq.travel.notificaciones.Observador;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.servicios.ServicioIndividual;
import ar.edu.unq.travel.traslados.Direccion;
import ar.edu.unq.travel.traslados.Traslado;

class ReservaTest {

	Reserva reserva;
	ServicioIndividual estadiaCancun;
	Traslado unTraslado;
	MetodoDePago unMetodoDePago;
	Estado estado;
	Direccion direccion;
	int cantPasajeros = 4;
	LocalDate inicio = LocalDate.of(2026, 11, 2);
	LocalDate fin = LocalDate.of(2026, 11, 10);
	PeriodoDeTiempo periodo;
	List<Observador> obs = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		// DOCS
		this.estadiaCancun = mock(ServicioIndividual.class);
		this.unTraslado = mock(Traslado.class);
		this.unMetodoDePago = mock(MetodoDePago.class);
		this.estado = mock(Estado.class);
		this.direccion = mock(Direccion.class);
		this.periodo = new PeriodoDeTiempo(inicio, fin);

		// SUT
		this.reserva = new Reserva(this.unTraslado, this.unMetodoDePago, periodo, cantPasajeros, direccion, obs);
	}

	// La reserva al hacer cambios de estado inválidos genera una excepción

	@Test
	void testAgregaUnServicioEnUnEstadoQueNoSeaPendienteGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		reserva.agregarServicio(estadiaCancun);
		reserva.confirmar();

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.agregarServicio(estadiaCancun));
		assertEquals("solo se pueden agregar servicios al iniciar la reserva", error.getMessage());
	}

	@Test
    void testAgregarAlCarritoAgregaServicioCuandoHayDisponibilidad()
            throws Exception {

        when(estadiaCancun.hayDisponibilidad(periodo, 2)).thenReturn(true);

        reserva.cambiarPeriodoDeTiempo(periodo);
        reserva.cantidadDePasajeros(2);

        reserva.agregarAlCarrito(estadiaCancun);

        assertTrue(this.reserva.getServicios().contains(estadiaCancun));
    }

	@Test
	void testQuitarUnServicioEnOtroEstadoQueNoSeaPendienteGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		this.reserva.agregarServicio(estadiaCancun);
		this.reserva.confirmar();

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.quitarServicio(estadiaCancun));
		assertEquals("solo se pueden quitar servicios al iniciar la reserva", error.getMessage());
	}

	@Test
	void testConfirmarUnaReservaEnUnEstadoQueNoSeaPendienteGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		this.reserva.agregarServicio(estadiaCancun);
		this.reserva.confirmar();
		this.reserva.enCheckin();

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.confirmar());
		assertEquals("no se puede confirmar la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testHacerCheckinDeLaReservaEnUnEstadoQueNoSeaConfirmadoGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		this.reserva.agregarServicio(estadiaCancun);

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.enCheckin());
		assertEquals("no se puede realizar checkin de la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testQueLaReservaEsteEnCursoDespuesDeUnEstadoQueNoSeaEnCheckinGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		this.reserva.agregarServicio(estadiaCancun);
		this.reserva.confirmar();

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.enCurso());
		assertEquals("no se puede poner en curso a la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testFinalizarUnaReservaDespuesDeUnEstadoQueNoSeaEnCursoGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		this.reserva.agregarServicio(estadiaCancun);

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.finalizar());
		assertEquals("no se puede finalizar la reserva en este estado de la compra", error.getMessage());
	}

	@Test
	void testCancelarUnaReservaDespuesDeFinalizadaGeneraUnaExcepcion() {
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		this.reserva.finalizar();

		IllegalStateException error =
			assertThrows(IllegalStateException.class, () -> this.reserva.cancelar());
		assertEquals("no se puede cancelar la reserva en este estado de la compra", error.getMessage());
	}

	// Cambios de estado válidos y manejo de List<Servicio>

	@Test
	void testLaReservaHaceUnCambioDeEstadoValidoModificaSuEstadoActualYElAnterior() {
		this.reserva.confirmar();
		assertEquals(Confirmada.class, this.reserva.getEstado().getClass());
		assertEquals(SolicitudPendiente.class, this.reserva.getEstadoAnterior().getClass());
	}



	@Test
	void testAgregaUnServicioNoGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		assertDoesNotThrow(() -> this.reserva.agregarServicio(estadiaCancun));
	}

	@Test
	void testAgregaUnServicioDelegaLaResponsabilidadAlEstado() {
		this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun);
		verify(this.estado).agregarServicio(this.reserva, this.estadiaCancun);
	}

	@Test
	void testUnaReservaAgregaUnServicioYApareceEnSuListaDeServicios() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);

		this.reserva.agregarServicio(this.estadiaCancun);
		assertTrue(this.reserva.getServicios().contains(estadiaCancun));
	}

	@Test
	void testQuitarUnServicioDeLaReservaNoGeneraUnaExcepcion() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		reserva.agregarServicio(estadiaCancun);
		assertDoesNotThrow(() -> this.reserva.quitarServicio(estadiaCancun));
	}

	@Test
	void testQuitarUnServicioDelegaLaResponsabilidadAlEstado() {
		this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun);
		this.reserva.quitarServicio(this.estadiaCancun);
		verify(this.estado).quitarServicio(this.reserva, this.estadiaCancun);
	}

	@Test
	void testUnaReservaQuitaUnServicioYYaNoApareceEnSuListaDeServicios() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);

		this.reserva.agregarServicio(this.estadiaCancun);
		this.reserva.quitarServicio(this.estadiaCancun);

		assertFalse(this.reserva.getServicios().contains(estadiaCancun));
	}

	@Test
	void testConfirmarLaReservaDelegaLaResponsabilidadAlEstado() {
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
	void testRealizarCheckinDespuesDeConfirmarLaReservaNoGeneraUnaExcepcion() {
		this.reserva.confirmar();
		assertDoesNotThrow(() -> this.reserva.enCheckin());
	}

	@Test
	void testRealizarCheckinDeLaReservaDelegaLaResponsabilidadAlEstado() {
		this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun);
		this.reserva.confirmar();
		this.reserva.enCheckin();
		verify(this.estado).enCheckin(this.reserva);
	}

	@Test
	void testReservaEnCursoDelegaLaResponsabilidadAlEstado() {
		this.reserva.cambiarAEstado(this.estado);
		this.reserva.agregarServicio(this.estadiaCancun);
		this.reserva.confirmar();
		this.reserva.enCheckin();
		this.reserva.enCurso();
		verify(this.estado).enCurso(this.reserva);
	}

	@Test
	void testReservaEnCursoDespuesDeEnCheckinNoGeneraUnaExcepcion() {
		this.reserva.cambiarAEstado(new EnCheckin());
		assertDoesNotThrow(() -> this.reserva.enCurso());
	}

	@Test
	void testFinalizarUnaReservaDelegaLaResponsabilidadAlEstado() {
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
	void testCancelarUnaReservaDelegaLaResponsabilidadAlEstado() {
		this.reserva.cambiarAEstado(this.estado);
		this.reserva.cancelar();
		verify(this.estado).cancelada(this.reserva);
	}

	@Test
	void testCostoDeServicioSumaLosServiciosAgregados() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		when(estadiaCancun.precioFinal()).thenReturn(1500.0);

		this.reserva.agregarServicio(this.estadiaCancun);

		assertEquals(1500.0, this.reserva.costoDeServicio());
	}

	@Test
	void testCostoDeServicioConTasasSumaCostoYTasas() {
		when(estadiaCancun.hayDisponibilidad(periodo, cantPasajeros)).thenReturn(true);
		when(estadiaCancun.precioFinal()).thenReturn(1500.0);

		this.reserva.agregarServicio(this.estadiaCancun);

		assertEquals(
			this.reserva.costoDeServicio() + this.reserva.tasasAdministrativas(),
			this.reserva.costoDeServicioConTasas()
		);
	}

	@Test
	void testLaReservaConoceSuCantidadDePersonas() {
		assertEquals(4, this.reserva.getCantidadPasajeros());
	}

	@Test
    void testCantidadDePasajerosModificaLaCantidad() {

        reserva.cantidadDePasajeros(5);

        assertEquals(5, reserva.getCantidadPasajeros());
    }

	@Test
	void testDireccionDelViaje() {
		this.reserva.cambiarDireccionDestino(this.direccion);
		assertEquals(this.direccion, this.reserva.direccionDestino());
	}
}