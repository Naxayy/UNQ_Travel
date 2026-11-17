package ar.edu.unq.travel.categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

public class AlquilerDeAutoTest {

    @Test
    public void testGetNombreDevuelveAlquilerDeAuto() {
        Categoria alquilerDeAuto = new AlquilerDeAuto();

        assertInstanceOf(Categoria.class, alquilerDeAuto);
        assertEquals("AlquilerDeAuto", alquilerDeAuto.getNombre());
    }
}