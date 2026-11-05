package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
