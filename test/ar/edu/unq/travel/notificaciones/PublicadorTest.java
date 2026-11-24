package ar.edu.unq.travel.notificaciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import ar.edu.unq.travel.ciclo.Estado;

public class PublicadorTest {

    private List<Observador> observadores;
    private Publicador publicador;
    private Estado estado;

    @BeforeEach
    public void setUp() {
        observadores = new ArrayList<>();
        publicador = new Publicador(observadores);
        estado = mock(Estado.class);
    }

    @Test
    public void testNotificarAObservadoresLlamaAcceptEnCadaObservador() {
        Observador obs1 = mock(Observador.class);
        Observador obs2 = mock(Observador.class);
        publicador.agregarObservador(obs1);
        publicador.agregarObservador(obs2);

        publicador.notificarAObservadores(estado);

        verify(obs1).accept(estado);
        verify(obs2).accept(estado);
    }

    @Test
    public void testEliminarObservadorEvitaQueSeaNotificado() {
        Observador obs1 = mock(Observador.class);
        Observador obs2 = mock(Observador.class);
        publicador.agregarObservador(obs1);
        publicador.agregarObservador(obs2);

        publicador.eliminarObservador(obs1);
        publicador.notificarAObservadores(estado);

        verifyNoInteractions(obs1);
        verify(obs2).accept(estado);
    }

    @Test
    public void testNotificarSinObservadoresNoFalla() {
        publicador.notificarAObservadores(estado);

        verifyNoInteractions(estado);
    }
}