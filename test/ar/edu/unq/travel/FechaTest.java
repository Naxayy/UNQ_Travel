package ar.edu.unq.travel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.atributosDinamicos.Fecha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

public class FechaTest {
    Fecha fechaDeCreacion;

    @BeforeEach 
    void SetUp(){
        this.fechaDeCreacion = new Fecha("fecha de creacion", false, new Date(10/10/2011));
    }

    @Test 
    void valorAsignadoEnFechaDeCreacion(){
        assertNotEquals(1992, fechaDeCreacion.getValor());
    }
}
