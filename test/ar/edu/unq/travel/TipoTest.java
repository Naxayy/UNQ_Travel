package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TipoTest {
    Booleano seAceptanMascotas;
    Numerico ratingEstrellas;
    Fecha fechaDeCreacion;
    Texto descripcionDelLugar;

    @BeforeEach 
    void SetUp(){
        this.seAceptanMascotas = new Booleano("politica mascotas", false, true);
        this.ratingEstrellas = new Numerico("Rating estrellas", true, 2);
        this.fechaDeCreacion = new Fecha("fecha de creacion", false, new Date(10/10/2011));
        this.descripcionDelLugar = new Texto("descripcion del lugar", true, "muy bonito");
    }

    @Test 
    void validarSeAceptanMascotasSinError(){
        assertDoesNotThrow(() -> seAceptanMascotas.validar());
    }

    @Test
    void validarRatingEstrellasSinError(){
        assertDoesNotThrow(() -> ratingEstrellas.validar());
    } 

    @Test
    void validarfechaDeCreacionSinError(){
        assertDoesNotThrow(() -> fechaDeCreacion.validar());
    } 

    @Test
    void validardescripcionDelLugarSinError(){
        assertDoesNotThrow(() -> descripcionDelLugar.validar());
    } 

    @Test 
    void hayValorAsignadoEnSeAceptanMascotas(){
        assertEquals(true, seAceptanMascotas.hayValorAsignado());
    }

    @Test 
    void hayValorAsignadoEnRatingEstrellas(){
        assertNotEquals(false, ratingEstrellas.hayValorAsignado());
    }

    @Test 
    void hayValorAsignadoEnFechaDeCreacion(){
        assertEquals(true, fechaDeCreacion.hayValorAsignado());
    }

    @Test 
    void hayValorAsignadoEnDescripcionDelLugar(){
        assertNotEquals(false, descripcionDelLugar.hayValorAsignado());
    }

    @Test 
    void valorAsignadoEnSeAceptanMascotas(){
        assertNotEquals(false, seAceptanMascotas.getValor());
    }

    @Test 
    void valorAsignadoEnRatingEstrellas(){
        assertEquals(2, ratingEstrellas.getValor());
    }

    @Test 
    void valorAsignadoEnFechaDeCreacion(){
        assertNotEquals(1992, fechaDeCreacion.getValor());
    }

    @Test 
    void valorAsignadoEnDescripcionDelLugar(){
        assertEquals("muy bonito", descripcionDelLugar.getValor());
    }
}
