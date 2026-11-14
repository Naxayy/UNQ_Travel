package ar.edu.unq.travel.atributosDinamicos;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TipoTest {
    Numerico ratingEstrellas;

    @BeforeEach 
    void SetUp(){
        //instancio un de las clase concreta de atributo para no repetir cogigo de testeo en cada uno de las clases hijas
        this.ratingEstrellas = new Numerico("Rating estrellas", true, 2);
    }


    @Test
    void validarRatingEstrellasSinError(){
        assertDoesNotThrow(() -> ratingEstrellas.validar());
    }

    @Test 
    void hayValorAsignadoEnRatingEstrellas(){
        assertNotEquals(false, ratingEstrellas.hayValorAsignado());
    }
}
