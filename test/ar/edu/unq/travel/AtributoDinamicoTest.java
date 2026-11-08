package ar.edu.unq.travel;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;

public class AtributoDinamicoTest {

    AtributoDinamico CapacidadMaxima;
    AtributoDinamico exclusivoMayoresDeEdad;

    @BeforeEach 
    void SetUp(){
        this.CapacidadMaxima = new AtributoDinamico("Capacidad maxima", false);
        this.exclusivoMayoresDeEdad = new AtributoDinamico("solo para mayores de 18", true);
    }

    @Test
    void atributoDinamicoSinError(){
        assertDoesNotThrow(() -> CapacidadMaxima.validar());
    }

    @Test
    void atributoDinamicoConError(){
        IllegalStateException error = 
            assertThrows(IllegalStateException.class, () -> exclusivoMayoresDeEdad.validar() );
            assertEquals("Es obligatorio y no tiene valor", error.getMessage());
    } 

    @Test 
    void noHayValorAsignadoEnAtributoDinamico(){
        assertEquals(false, CapacidadMaxima.hayValorAsignado());
    }

    @Test 
    void nombreDeUnAtributo(){
        assertEquals("Capacidad maxima", CapacidadMaxima.getNombre());
    }

    @Test 
    void obligatoriedadeDeUnAtributo(){
        assertEquals(true, exclusivoMayoresDeEdad.getObligatoriedad());
    }

    @Test 
    void CambioDeObligoriedad(){
        exclusivoMayoresDeEdad.setObligatoriedad(false);      
        assertEquals(false, exclusivoMayoresDeEdad.getObligatoriedad());
    }
    
}

