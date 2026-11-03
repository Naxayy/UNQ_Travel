package ar.edu.unq.travel;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class AtributoDinamicoTest {

    AtributoDinamico atributo;
    Reserva reserva;

    @BeforeEach 
    void SetUp(){
        this.reserva = mock(Reserva.class);

        this.atributo = new AtributoDinamico("Capacidad máxima", True, );
    }
}
