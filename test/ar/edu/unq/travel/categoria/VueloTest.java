package ar.edu.unq.travel.categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

public class VueloTest {

    @Test
    public void testGetNombreDevuelveVuelo() {
        Categoria vuelo = new Vuelo();

        assertInstanceOf(Categoria.class, vuelo);
        assertEquals("Vuelo", vuelo.getNombre());
    }
}