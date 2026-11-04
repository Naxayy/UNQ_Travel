package ar.edu.unq.travel;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AtributoDinamicoTest {

    AtributoDinamico CapacidadMáxima;
    AtributoDinamico seAceptanMascotas;
    AtributoDinamico RatingEstrellas;
    AtributoDinamico exclusivoMayoresDeEdad;

    @BeforeEach 
    void SetUp(){
        this.CapacidadMáxima = new AtributoDinamico("Capacidad máxima", false);
        this.seAceptanMascotas = new Booleano("politica mascotas", true, false);
        this.RatingEstrellas = new Numerico("rating estrellas", false, 5);
        this.exclusivoMayoresDeEdad = new AtributoDinamico("solo para mayores de 18", true);
    }

    @Test
    void atributoDinamicoSinError(){
        assertDoesNotThrow(() -> CapacidadMáxima.validar());
    }

    @Test 
    void HerenciaDeAtributoABoolenoSinError(){
        assertDoesNotThrow(() -> CapacidadMáxima.validar());
    }

    @Test
    void HerenciaDeAtributoANumericoSinError(){
        assertDoesNotThrow(() -> CapacidadMáxima.validar());
    } 

     @Test
    void atributoDinamicoConError(){
        IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> exclusivoMayoresDeEdad.validar() );
            assertEquals("Es obligatorio y no tiene valor", error.getMessage());
    } 
}

