
package ar.edu.unq.travel.servicios;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProveedorTest {

    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        proveedor = new Proveedor("Despegar");
    }

    @Test
    void testGetNombreDevuelveElNombreDelProveedor() {
        assertEquals("Despegar", proveedor.getNombre());
    }
}