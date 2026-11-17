package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ar.edu.unq.travel.notificaciones.Observador;
import ar.edu.unq.travel.notificaciones.Publicador;

public class PublicadorTest {
    private Publicador publicador;
    private Reserva reserva; 
    private Observador observador;
    private List<Observador> observadores;


    @BeforeEach 
    void SetUp(){
        this.reserva = mock(Reserva.class);
        this.observador = mock(Observador.class); 
        this.observadores = new ArrayList<> (List.of(observador));

        this.publicador = new Publicador(observadores);
    }

    @Test 
    void testNotificarObservadores(){
        this.publicador.notificarAObservadores();

        verify(observador).actualizar();
    }

    @Test
    void testAgregarObservador(){
        Observador nuevoObservador = mock(Observador.class);
        this.publicador.agregarObservador(nuevoObservador);
        this.publicador.notificarAObservadores();

        verify(observador).actualizar();
        verify(nuevoObservador).actualizar();
    }

    @Test
    void testEliminarObservador(){
        

    }

}
