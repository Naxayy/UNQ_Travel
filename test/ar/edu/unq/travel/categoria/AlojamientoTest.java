package ar.edu.unq.travel.categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

public class AlojamientoTest {

    @Test
    public void testGetNombreDevuelveAlojamiento() {
        Categoria alojamiento = new Alojamiento();

        assertInstanceOf(Categoria.class, alojamiento);
        assertEquals("Alojamiento", alojamiento.getNombre());
    }
}