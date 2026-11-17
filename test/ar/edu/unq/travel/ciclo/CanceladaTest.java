package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CanceladaTest {
    private Cancelada estadoCancelada;

    @BeforeEach
    void setUp() {
        estadoCancelada = new Cancelada();
    }

    @Test
    void testUnEstadoCanceladaNoOcupaCupo() {
        assertFalse(estadoCancelada.ocupaCupo());
    }
}
