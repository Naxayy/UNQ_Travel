package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.servicios.Servicio;

public class ReporteServiciosMasReservadosTest {

    private PeriodoDeTiempo periodo;
    private Estado activa;
    private Estado inactiva;

    @BeforeEach
    public void setUp() {
        periodo = new PeriodoDeTiempo(LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 30));

        activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        inactiva = mock(Estado.class);
        when(inactiva.ocupaCupo()).thenReturn(false);
    }

    private Reserva mockReserva(Estado estado, LocalDate inicio, LocalDate fin, int pasajeros, List<Servicio> servicios) {
        Reserva reserva = mock(Reserva.class);
        when(reserva.getEstado()).thenReturn(estado);
        when(reserva.getPeridoDeTiempo()).thenReturn(new PeriodoDeTiempo(inicio, fin));
        when(reserva.fechaDeInicio()).thenReturn(inicio);
        when(reserva.fechaFin()).thenReturn(fin);
        when(reserva.getCantidadPasajeros()).thenReturn(pasajeros);
        when(reserva.getServicios()).thenReturn(servicios);
        return reserva;
    }

    @Test
    public void testGetDatosOrdenaServiciosDeMayorAMenorCantidad() {
        Servicio excursion = mock(Servicio.class);
        when(excursion.getNombre()).thenReturn("Excursión");
        when(excursion.precioFinal()).thenReturn(500.0);

        Servicio hotel = mock(Servicio.class);
        when(hotel.getNombre()).thenReturn("Hotel Sol");
        when(hotel.precioFinal()).thenReturn(1500.0);

        Reserva r1 = mockReserva(activa, LocalDate.of(2026, 11, 5), LocalDate.of(2026, 11, 7), 2, List.of(excursion));
        Reserva r2 = mockReserva(activa, LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 12), 5, List.of(hotel));

        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(List.of(r1, r2), periodo);
        List<DatoServicio> datos = reporte.getDatos();

        assertEquals(2, datos.size());
        assertEquals("Hotel Sol", datos.get(0).getNombre());
        assertEquals(5, datos.get(0).getCantidad());
        assertEquals("Excursión", datos.get(1).getNombre());
        assertEquals(2, datos.get(1).getCantidad());
    }

    @Test
    public void testIncluyeReservasConSuperposicionParcialEnPeriodo() {
        Servicio hotel = mock(Servicio.class);
        when(hotel.getNombre()).thenReturn("Hotel Sol");
        when(hotel.precioFinal()).thenReturn(1500.0);

        Reserva parcial = mockReserva(activa, LocalDate.of(2026, 10, 28), LocalDate.of(2026, 11, 2), 3, List.of(hotel));

        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(List.of(parcial), periodo);

        assertEquals(1, reporte.getDatos().size());
        assertEquals("Hotel Sol", reporte.getDatos().get(0).getNombre());
        assertEquals(3, reporte.getDatos().get(0).getCantidad());
    }

    @Test
    public void testIgnoraReservasInactivasYFueraDePeriodo() {
        Servicio hotel = mock(Servicio.class);
        when(hotel.getNombre()).thenReturn("Hotel Sol");

        Reserva cancelada = mockReserva(inactiva, LocalDate.of(2026, 11, 5), LocalDate.of(2026, 11, 7), 2, List.of(hotel));
        Reserva fueraDePeriodo = mockReserva(activa, LocalDate.of(2026, 12, 1), LocalDate.of(2026, 12, 5), 4, List.of(hotel));

        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(List.of(cancelada, fueraDePeriodo), periodo);

        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testAcceptDelegaEnVisitor() {
        Visitor visitor = mock(Visitor.class);
        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(List.of(), periodo);
        when(visitor.visitServiciosMasReservados(reporte)).thenReturn("reporte-servicios");

        String resultado = reporte.accept(visitor);

        assertEquals("reporte-servicios", resultado);
        verify(visitor).visitServiciosMasReservados(reporte);
    }
}