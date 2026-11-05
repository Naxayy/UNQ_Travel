package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BooleanTest {
    Booleano seAceptanMascotas;

    @BeforeEach 
    void SetUp(){
        //instancio un clase concreta de atributo para no repetir cogigo de testeo en cada uno de las clases hijas
        this.seAceptanMascotas = new Booleano("politica mascotas", false, true);
    }

    @Test 
    void valorAsignadoEnSeAceptanMascotas(){
        assertNotEquals(false, seAceptanMascotas.getValor());
    }
}
