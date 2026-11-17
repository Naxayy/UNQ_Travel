package ar.edu.unq.travel.reportes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExportadorHtmlTest {

    private ExportadorHtml exportador;

    @BeforeEach
    public void setUp() {
        exportador = new ExportadorHtml();
    }

    @Test
    public void testVisitServiciosMasReservados() {
        DatoServicio dato = mock(DatoServicio.class);
        when(dato.getNombre()).thenReturn("Hotel Sol");
        when(dato.getCantidad()).thenReturn(4);
        when(dato.getPrecioPromedio()).thenReturn(1500.0);

        ReporteServiciosMasReservados reporte = mock(ReporteServiciosMasReservados.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "<table><tr><th>Item</th><th>Vendidos</th><th>Precio Promedio</th></tr>"
                + "<tr><td>Hotel Sol</td><td>4</td><td>1500.0</td></tr></table>";
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

        String esperado = "<table><tr><th>Categoria</th><th>Totales</th><th>Reservados</th><th>Libres</th></tr>"
                + "<tr><td>Alojamiento</td><td>10</td><td>4</td><td>6</td></tr></table>";
        assertEquals(esperado, exportador.visitOcupacionCupos(reporte));
    }

    @Test
    public void testVisitExposicionProveedor() {
        DatoProveedor dato = mock(DatoProveedor.class);
        when(dato.getOperador()).thenReturn("Despegar");
        when(dato.getPorcentaje()).thenReturn(50.0);

        ReporteExposicionProveedor reporte = mock(ReporteExposicionProveedor.class);
        when(reporte.getDatos()).thenReturn(List.of(dato));

        String esperado = "<table><tr><th>Operador</th><th>Porcentaje</th></tr>"
                + "<tr><td>Despegar</td><td>50.0</td></tr></table>";
        assertEquals(esperado, exportador.visitExposicionProveedor(reporte));
    }
}