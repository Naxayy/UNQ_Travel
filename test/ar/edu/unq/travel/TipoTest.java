package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertDoesNotThrow(() -> fechaDeCreacion.validar());
    } 

    @Test
    void validarTextoSinError(){
        assertDoesNotThrow(() -> descripcionDelLugar.validar());
    } 

    @Test 
    void hayValorAsignadoEnBooleano(){
        assertEquals(true, seAceptanMascotas.hayValorAsignado());
    }

    @Test 
    void hayValorAsignadoEnNumerico(){
        assertNotEquals(false, RatingEstrellas.hayValorAsignado());
    }

    @Test 
    void hayValorAsignadoEnFecha(){
        assertEquals(true, fechaDeCreacion.hayValorAsignado());
    }

    @Test 
    void hayValorAsignadoEnTexto(){
        assertNotEquals(false, descripcionDelLugar.hayValorAsignado());
    }

    @Test 
    void valorAsignadoEnBooleano(){
        assertNotEquals(false, seAceptanMascotas.getValor());
    }

    @Test 
    void valorAsignadoEnNumerico(){
        assertEquals(2, RatingEstrellas.getValor());
    }

    @Test 
    void valorAsignadoEnFecha(){
        assertNotEquals(1992, fechaDeCreacion.getValor());
    }

    @Test 
    void valorAsignadoEnTexto(){
        assertEquals("muy bonito", descripcionDelLugar.getValor());
    }
}
