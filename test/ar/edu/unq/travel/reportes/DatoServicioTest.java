package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoServicioTest {

    private Servicio hotel;
    private LocalDate desde;
    private LocalDate hasta;

    @BeforeEach
    public void setUp() {
        hotel = mock(Servicio.class);
        when(hotel.getNombre()).thenReturn("Hotel Sol");
        when(hotel.precioFinal()).thenReturn(1500.0);
        desde = LocalDate.of(2026, 11, 1);
        hasta = LocalDate.of(2026, 11, 30);
    }

    @Test
    public void testGetNombreCantidadYPrecioPromedio() {
        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Reserva r1 = mock(Reserva.class);
        when(r1.getEstado()).thenReturn(activa);
        when(r1.getFechaInicio()).thenReturn(LocalDate.of(2026, 11, 5));
        when(r1.getFechaFin()).thenReturn(LocalDate.of(2026, 11, 7));
        when(r1.getCantidadPasajeros()).thenReturn(2);
        when(r1.getServicios()).thenReturn(List.of(hotel));

        Reserva r2 = mock(Reserva.class);
        when(r2.getEstado()).thenReturn(activa);
        when(r2.getFechaInicio()).thenReturn(LocalDate.of(2026, 11, 10));
        when(r2.getFechaFin()).thenReturn(LocalDate.of(2026, 11, 12));
        when(r2.getCantidadPasajeros()).thenReturn(3);
        when(r2.getServicios()).thenReturn(List.of(hotel));

        DatoServicio dato = new DatoServicio(hotel, List.of(r1, r2), desde, hasta);

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

        DatoServicio dato = new DatoServicio(hotel, List.of(r1), desde, hasta);

        assertEquals(0, dato.getCantidad());
        assertEquals(0.0, dato.getPrecioPromedio());
    }
}