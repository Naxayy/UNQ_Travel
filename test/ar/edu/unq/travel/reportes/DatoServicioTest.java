package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.PeriodoDeTiempo; // ajustá el paquete si PeriodoDeTiempo está en otro subpaquete
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoServicioTest {

    private Servicio hotel;
    private PeriodoDeTiempo periodo;

    @BeforeEach
    public void setUp() {
        hotel = mock(Servicio.class);
        when(hotel.getNombre()).thenReturn("Hotel Sol");
        when(hotel.precioFinal()).thenReturn(1500.0);

        periodo = new PeriodoDeTiempo(LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 30));
    }

    @Test
    public void testGetNombreCantidadYPrecioPromedio() {
        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Reserva r1 = mock(Reserva.class);
        when(r1.getEstado()).thenReturn(activa);
        when(r1.fechaDeInicio()).thenReturn(LocalDate.of(2026, 11, 5));
        when(r1.fechaFin()).thenReturn(LocalDate.of(2026, 11, 7));
        when(r1.getCantidadPasajeros()).thenReturn(2);
        when(r1.getServicios()).thenReturn(List.of(hotel));

        Reserva r2 = mock(Reserva.class);
        when(r2.getEstado()).thenReturn(activa);
        when(r2.fechaDeInicio()).thenReturn(LocalDate.of(2026, 11, 10));
        when(r2.fechaFin()).thenReturn(LocalDate.of(2026, 11, 12));
        when(r2.getCantidadPasajeros()).thenReturn(3);
        when(r2.getServicios()).thenReturn(List.of(hotel));

        DatoServicio dato = new DatoServicio(hotel, List.of(r1, r2), periodo);

        assertEquals("Hotel Sol", dato.getNombre());
        assertEquals(5, dato.getCantidad());
        assertEquals(1500.0, dato.getPrecioPromedio());
    }

    @Test
    public void testSinReservasValidasDevuelveCero() {
        Estado inactiva = mock(Estado.class);
        when(inactiva.ocupaCupo()).thenReturn(false);

        Reserva r1 = mock(Reserva.class);
        when(r1.getEstado()).thenReturn(inactiva);

        DatoServicio dato = new DatoServicio(hotel, List.of(r1), periodo);

        assertEquals(0, dato.getCantidad());
        assertEquals(0.0, dato.getPrecioPromedio());
    }

    @Test
    public void testIncluyeReservasConSuperposicionParcialEnBordes() {
        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        // Empieza antes del período y termina dentro (28/10 al 02/11)
        Reserva bordeInicio = mock(Reserva.class);
        when(bordeInicio.getEstado()).thenReturn(activa);
        when(bordeInicio.fechaDeInicio()).thenReturn(LocalDate.of(2026, 10, 28));
        when(bordeInicio.fechaFin()).thenReturn(LocalDate.of(2026, 11, 2));
        when(bordeInicio.getCantidadPasajeros()).thenReturn(2);
        when(bordeInicio.getServicios()).thenReturn(List.of(hotel));

        // Empieza dentro del período y termina después (28/11 al 03/12)
        Reserva bordeFin = mock(Reserva.class);
        when(bordeFin.getEstado()).thenReturn(activa);
        when(bordeFin.fechaDeInicio()).thenReturn(LocalDate.of(2026, 11, 28));
        when(bordeFin.fechaFin()).thenReturn(LocalDate.of(2026, 12, 3));
        when(bordeFin.getCantidadPasajeros()).thenReturn(4);
        when(bordeFin.getServicios()).thenReturn(List.of(hotel));

        DatoServicio dato = new DatoServicio(hotel, List.of(bordeInicio, bordeFin), periodo);

        assertEquals(6, dato.getCantidad());
        assertEquals(1500.0, dato.getPrecioPromedio());
    }

    @Test
    public void testIgnoraReservasFueraDePeriodo() {
        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Reserva anterior = mock(Reserva.class);
        when(anterior.getEstado()).thenReturn(activa);
        when(anterior.fechaDeInicio()).thenReturn(LocalDate.of(2026, 10, 20));
        when(anterior.fechaFin()).thenReturn(LocalDate.of(2026, 10, 31));
        when(anterior.getCantidadPasajeros()).thenReturn(2);
        when(anterior.getServicios()).thenReturn(List.of(hotel));

        Reserva posterior = mock(Reserva.class);
        when(posterior.getEstado()).thenReturn(activa);
        when(posterior.fechaDeInicio()).thenReturn(LocalDate.of(2026, 12, 1));
        when(posterior.fechaFin()).thenReturn(LocalDate.of(2026, 12, 5));
        when(posterior.getCantidadPasajeros()).thenReturn(3);
        when(posterior.getServicios()).thenReturn(List.of(hotel));

        DatoServicio dato = new DatoServicio(hotel, List.of(anterior, posterior), periodo);

        assertEquals(0, dato.getCantidad());
        assertEquals(0.0, dato.getPrecioPromedio());
    }
}