package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class FidelizacionTest {

    private Fidelizacion fidelizacion;

    @BeforeEach
    public void setUp() {
        fidelizacion = new Fidelizacion();
    }

    @Test
    public void testAcceptDelegaEnVisitarFidelizacionDelEstado() {
        Estado estado = mock(Estado.class);

        fidelizacion.accept(estado);

        verify(estado).visitarFidelizacion(fidelizacion);
    }

    @Test
    public void testGenerarCupoNoLanzaExcepcion() {
        assertDoesNotThrow(() -> fidelizacion.generarCupo());
    }
}