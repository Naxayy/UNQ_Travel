package ar.edu.unq.travel.atributosDinamicos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
