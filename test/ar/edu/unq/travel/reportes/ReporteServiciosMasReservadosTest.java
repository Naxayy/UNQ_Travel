package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.servicios.Servicio;

public class ReporteServiciosMasReservadosTest {

    @Test
    public void testOrdenaServiciosDeMayorAMenorCantidadYAceptaVisitor() {
        LocalDate desde = LocalDate.of(2026, 11, 1);
        LocalDate hasta = LocalDate.of(2026, 11, 30);

        Servicio menosVendido = mock(Servicio.class);
        when(menosVendido.getNombre()).thenReturn("Excursion");

        Servicio masVendido = mock(Servicio.class);
        when(masVendido.getNombre()).thenReturn("Vuelo");

        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Reserva r1 = mock(Reserva.class);
        when(r1.getEstado()).thenReturn(activa);
        when(r1.getFechaInicio()).thenReturn(LocalDate.of(2026, 11, 2));
        when(r1.getFechaFin()).thenReturn(LocalDate.of(2026, 11, 4));
        when(r1.getCantPersonas()).thenReturn(1);
        when(r1.getServicios()).thenReturn(List.of(menosVendido));

        Reserva r2 = mock(Reserva.class);
        when(r2.getEstado()).thenReturn(activa);
        when(r2.getFechaInicio()).thenReturn(LocalDate.of(2026, 11, 5));
        when(r2.getFechaFin()).thenReturn(LocalDate.of(2026, 11, 8));
        when(r2.getCantPersonas()).thenReturn(4);
        when(r2.getServicios()).thenReturn(List.of(masVendido));

        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(List.of(r1, r2), desde, hasta);

        assertEquals(2, reporte.getDatos().size());
        assertEquals("Vuelo", reporte.getDatos().get(0).getNombre());
        assertEquals("Excursion", reporte.getDatos().get(1).getNombre());

        Visitor visitor = mock(Visitor.class);
        when(visitor.visitServiciosMasReservados(reporte)).thenReturn("ok");
        assertEquals("ok", reporte.accept(visitor));
        verify(visitor).visitServiciosMasReservados(reporte);
    }
}