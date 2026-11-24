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

    private Reserva reserva;
    private Fidelizacion fidelizacion;

    @BeforeEach
    public void setUp() {
        reserva = mock(Reserva.class);
        fidelizacion = new Fidelizacion(reserva);
    }

    @Test
    public void testGetYSetReserva() {
        assertEquals(reserva, fidelizacion.getReserva());

        Reserva nuevaReserva = mock(Reserva.class);
        fidelizacion.setReserva(nuevaReserva);

        assertEquals(nuevaReserva, fidelizacion.getReserva());
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