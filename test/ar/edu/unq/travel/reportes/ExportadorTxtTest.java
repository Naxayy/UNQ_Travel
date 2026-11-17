package ar.edu.unq.travel.reportes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExportadorTxtTest {

    private ExportadorTxt exportador;

    @BeforeEach
    public void setUp() {
        exportador = new ExportadorTxt();
    }

    @Test
    public void testVisitServiciosMasReservados() {
        DatoServicio dato = mock(DatoServicio.class);
        when(dato.getNombre()).thenReturn("Hotel Sol");
        when(dato.getCantidad()).thenReturn(4);
        when(dato.getPrecioPromedio()).thenReturn(1500.0);

        ReporteServiciosMasReservados reporte = mock(ReporteServiciosMasReservados.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "Reporte de Servicios Mas Reservados\n"
                + "Hotel Sol - Vendidos: 4 - Precio promedio: 1500.0\n";
        assertEquals(esperado, exportador.visitServiciosMasReservados(reporte));
    }

    @Test
    public void testVisitOcupacionCupos() {
        DatoCupo dato = mock(DatoCupo.class);
        when(dato.getCategoria()).thenReturn("Alojamiento");
        when(dato.getTotales()).thenReturn(10);
        when(dato.getReservados()).thenReturn(4);
        when(dato.getLibres()).thenReturn(6);

        ReporteOcupacionCupos reporte = mock(ReporteOcupacionCupos.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "Reporte de Ocupacion y Cupos\n"
                + "Alojamiento - Totales: 10 - Reservados: 4 - Libres: 6\n";
        assertEquals(esperado, exportador.visitOcupacionCupos(reporte));
    }

    @Test
    public void testVisitExposicionProveedor() {
        DatoProveedor dato = mock(DatoProveedor.class);
        when(dato.getOperador()).thenReturn("Despegar");
        when(dato.getPorcentaje()).thenReturn(50.0);

        ReporteExposicionProveedor reporte = mock(ReporteExposicionProveedor.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "Reporte de Exposicion por Proveedor\n"
                + "Despegar - Porcentaje: 50.0%\n";
        assertEquals(esperado, exportador.visitExposicionProveedor(reporte));
    }
}