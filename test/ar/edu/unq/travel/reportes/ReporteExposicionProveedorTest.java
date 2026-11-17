package ar.edu.unq.travel.reportes;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.servicios.Proveedor;

public class ReporteExposicionProveedorTest {

    @Test
    public void testConstruyeDatosDeProveedoresYAceptaVisitor() {
        Proveedor p1 = mock(Proveedor.class);
        Proveedor p2 = mock(Proveedor.class);
        when(p1.getNombre()).thenReturn("Aerolineas");
        when(p2.getNombre()).thenReturn("Booking");

        Catalogo catalogo = mock(Catalogo.class);
        when(catalogo.proveedores()).thenReturn(Set.of(p1, p2));

        Visitor visitor = mock(Visitor.class);
        when(visitor.visitExposicionProveedor(org.mockito.ArgumentMatchers.any())).thenReturn("ok");

        ReporteExposicionProveedor reporte = new ReporteExposicionProveedor(catalogo);

        assertEquals(2, reporte.getDatos().size());
        assertEquals("ok", reporte.accept(visitor));
        verify(visitor).visitExposicionProveedor(reporte);
    }
}