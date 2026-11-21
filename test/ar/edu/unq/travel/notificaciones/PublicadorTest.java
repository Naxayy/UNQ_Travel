package ar.edu.unq.travel.notificaciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

public class PublicadorTest {

    private Observador observador1;
    private Observador observador2;
    private Publicador publicador;

    @BeforeEach
    public void setUp() {
        observador1 = mock(Observador.class);
        observador2 = mock(Observador.class);
        publicador = new Publicador(new ArrayList<>(List.of(observador1, observador2)));
    }

    @Test
    public void testNotificarAObservadoresNotificaATodosLosSuscriptos() {
        publicador.notificarAObservadores();

        verify(observador1).actualizar();
        verify(observador2).actualizar();
    }

    @Test
    public void testAgregarObservadorLoIncluyeEnLasSiguientesNotificaciones() {
        Observador observador3 = mock(Observador.class);

        publicador.agregarObservador(observador3);
        publicador.notificarAObservadores();

        verify(observador1).actualizar();
        verify(observador2).actualizar();
        verify(observador3).actualizar();
    }

    @Test
    public void testEliminarObservadorDejaDeNotificarlo() {
        publicador.eliminarObservador(observador2);
        publicador.notificarAObservadores();

        verify(observador1).actualizar();
        verify(observador2, never()).actualizar();
    }

    @Test 
    void testNotificarObservadores(){
        this.publicador.notificarAObservadores();

        verify(observador1).actualizar();
    }

    @Test
    void testAgregarObservador(){
        Observador nuevoObservador = mock(Observador.class);
        this.publicador.agregarObservador(nuevoObservador);
        this.publicador.notificarAObservadores();

        verify(observador1).actualizar();
        verify(nuevoObservador).actualizar();
    }

    @Test
    void testEliminarObservador(){
        Observador nuevoObservador = mock(Observador.class);
        this.publicador.agregarObservador(nuevoObservador);
        this.publicador.eliminarObservador(nuevoObservador);
        this.publicador.notificarAObservadores();
        verifyNoInteractions(nuevoObservador);

    }
}