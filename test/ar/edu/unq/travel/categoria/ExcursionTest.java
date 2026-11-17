package ar.edu.unq.travel.categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

public class ExcursionTest {

    @Test
    public void testGetNombreDevuelveExcursion() {
        Categoria excursion = new Excursion();

        assertInstanceOf(Categoria.class, excursion);
        assertEquals("Excursion", excursion.getNombre());
    }
}