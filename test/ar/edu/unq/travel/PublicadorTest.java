package ar.edu.unq.travel;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.notificaciones.Observador;
import ar.edu.unq.travel.notificaciones.Publicador;

public class PublicadorTest {
    private Publicador publicador;
    private Reserva reserva; 
    private Observador observador; 

    @BeforeEach 
    void SetUp(){
        this.reserva = mock(Reserva.class);
        this.observador = mock(Observador.class); 

        this.publicador = new Publicador();
    }

    @Test 
    void testLaReservaCambioDeEstadoYLosOyentesReaccionan(){

    }

}
