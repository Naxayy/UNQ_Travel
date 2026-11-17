package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class FidelizacionTest {

    private Reserva reserva;
    private Estado estado;
    private Fidelizacion fidelizacion;

    @BeforeEach
    public void setUp() {
        reserva = mock(Reserva.class);
        estado = mock(Estado.class);
        when(reserva.getEstado()).thenReturn(estado);
        fidelizacion = new Fidelizacion(reserva);
    }

    @Test
    public void testGetYSetReserva() {
        assertEquals(reserva, fidelizacion.getReserva());

        Reserva otraReserva = mock(Reserva.class);
        fidelizacion.setReserva(otraReserva);

        assertEquals(otraReserva, fidelizacion.getReserva());
    }

    @Test
    public void testActualizarCuandoElEstadoEsFidelizador() {
        when(estado.esFidelizador()).thenReturn(true);

        fidelizacion.actualizar();

        verify(reserva).getEstado();
        verify(estado).esFidelizador();
    }

    @Test
    public void testActualizarCuandoElEstadoNoEsFidelizador() {
        when(estado.esFidelizador()).thenReturn(false);

        fidelizacion.actualizar();

        verify(reserva).getEstado();
        verify(estado).esFidelizador();
    }
}