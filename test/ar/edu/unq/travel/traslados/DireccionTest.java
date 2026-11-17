package ar.edu.unq.travel.traslados;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DireccionTest {

    @Test
    void testSePuedeCrearUnaDireccion() {
        Direccion direccion = new Direccion();

        assertNotNull(direccion);
    }
}