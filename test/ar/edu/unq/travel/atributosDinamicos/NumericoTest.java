package ar.edu.unq.travel.atributosDinamicos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.atributosDinamicos.Numerico;

public class NumericoTest {
    Numerico ratingEstrellas;

    @BeforeEach 
    void SetUp(){
        this.ratingEstrellas = new Numerico("Rating estrellas", true, 2);
    }

    @Test 
    void valorAsignadoEnRatingEstrellas(){
        assertEquals(2, ratingEstrellas.getValor());
    }
}
