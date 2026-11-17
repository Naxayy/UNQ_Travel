package ar.edu.unq.travel.reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.servicios.Proveedor;

public class DatoProveedorTest {

    @Test
    public void testGetProveedorOperadorYPorcentaje() {
        Proveedor proveedor = mock(Proveedor.class);
        when(proveedor.getNombre()).thenReturn("Despegar");

        Catalogo catalogo = mock(Catalogo.class);
        when(catalogo.porcentajeDeProveedor(proveedor)).thenReturn(40.0);

        DatoProveedor dato = new DatoProveedor(proveedor, catalogo);

        assertEquals("Despegar", dato.getProveedor());
        assertEquals("Despegar", dato.getOperador());
        assertEquals(40.0, dato.getPorcentaje());
    }
}