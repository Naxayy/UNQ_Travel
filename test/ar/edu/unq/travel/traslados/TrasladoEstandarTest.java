package ar.edu.unq.travel.traslados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Reserva;

public class TrasladoEstandarTest {
    Reserva unaReserva;
    PlataformaTransporte unaPlataforma;
    Direccion unaDireccion;
    TrasladoEstandar miTraslado;

    @BeforeEach
    public void setUp() {
        this.unaPlataforma = mock(PlataformaTransporte.class);
        this.unaReserva = mock(Reserva.class);
        this.miTraslado = new TrasladoEstandar(unaPlataforma);
        this.unaDireccion = mock(Direccion.class);
    }

    @Test
    void primerTest() {
        when(unaReserva.pasajeros()).thenReturn(3);
        when(unaReserva.direccionDestino()).thenReturn(unaDireccion);

        verify(unaPlataforma).estimarTraslado(unaReserva.pasajeros(), unaDireccion);
    }
    
}
