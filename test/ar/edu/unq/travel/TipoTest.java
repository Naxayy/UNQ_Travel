package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TipoTest {
    Booleano seAceptanMascotas;
    Numerico RatingEstrellas;
    Fecha fechaDeCreacion;
    Texto descripcionDelLugar;

    @BeforeEach 
    void SetUp(){
        this.seAceptanMascotas = new Booleano("politica mascotas", false, true);
        this.RatingEstrellas = new Numerico("Rating estrellas", true, 2);
        this.fechaDeCreacion = new Fecha("fecha de creacion", false, new Date(10/10/2011));
        this.descripcionDelLugar = new Texto("descripcion del lugar", true, "muy bonito");
    }

    @Test 
    void validarBooleanoSinError(){
        assertDoesNotThrow(() -> seAceptanMascotas.validar());
    }

    @Test
    void validarNumeriucoSinError(){
        assertDoesNotThrow(() -> RatingEstrellas.validar());
    } 

    @Test
    void validarFechaSinError(){
        assertDoesNotThrow(() -> RatingEstrellas.validar());
    } 

    @Test
    void validarTextoSinError(){
        assertDoesNotThrow(() -> RatingEstrellas.validar());
    } 

    @Test 
    void hayValorAsignadoEnNumerico(){
        assertEquals(true, RatingEstrellas.hayValorAsignado());
    }
}
