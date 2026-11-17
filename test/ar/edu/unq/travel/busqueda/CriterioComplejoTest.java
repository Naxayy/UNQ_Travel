package ar.edu.unq.travel.busqueda;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioComplejoTest {

    @Test
    public void testInicializaListaDeCriteriosEImplementaCriterioBusqueda() {
        CriterioBusqueda c1 = mock(CriterioBusqueda.class);
        CriterioBusqueda c2 = mock(CriterioBusqueda.class);
        List<CriterioBusqueda> lista = List.of(c1, c2);

        CriterioComplejo complejo = new CriterioComplejo(lista) {
            @Override
            public boolean satisface(Servicio item) {
                return true;
            }
        };

        assertInstanceOf(CriterioBusqueda.class, complejo);
        assertEquals(2, complejo.getCriterios().size());
        assertTrue(complejo.satisface(mock(Servicio.class)));
    }
}