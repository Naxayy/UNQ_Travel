package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.atributosDinamicos.Texto;

public class TextoTest {
    Texto descripcionDelLugar;

    @BeforeEach 
    void SetUp(){
        this.descripcionDelLugar = new Texto("descripcion del lugar", true, "muy bonito");
    }

    @Test 
    void valorAsignadoEnDescripcionDelLugar(){
        assertEquals("muy bonito", descripcionDelLugar.getValor());
    }
}
