package ar.edu.unq.travel.reportes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExportadorCsvTest {

    private ExportadorCsv exportador;

    @BeforeEach
    public void setUp() {
        exportador = new ExportadorCsv();
    }

    @Test
    public void testVisitServiciosMasReservados() {
        DatoServicio dato = mock(DatoServicio.class);
        when(dato.getNombre()).thenReturn("Hotel Sol");
        when(dato.getCantidad()).thenReturn(4);
        when(dato.getPrecioPromedio()).thenReturn(1500.0);

        ReporteServiciosMasReservados reporte = mock(ReporteServiciosMasReservados.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "Item,Vendidos,PrecioPromedio\nHotel Sol,4,1500.0\n";
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

        String esperado = "Categoria,Totales,Reservados,Libres\nAlojamiento,10,4,6\n";
        assertEquals(esperado, exportador.visitOcupacionCupos(reporte));
    }

    @Test
    public void testVisitExposicionProveedor() {
        DatoProveedor dato = mock(DatoProveedor.class);
        when(dato.getOperador()).thenReturn("Despegar");
        when(dato.getPorcentaje()).thenReturn(50.0);

        ReporteExposicionProveedor reporte = mock(ReporteExposicionProveedor.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "Operador,Porcentaje\nDespegar,50.0\n";
        assertEquals(esperado, exportador.visitExposicionProveedor(reporte));
    }
}