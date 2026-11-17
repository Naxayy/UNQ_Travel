package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.categoria.Categoria;

public class ReporteOcupacionCuposTest {

    @Test
    public void testConstruyeDatosPorCategoriaYAceptaVisitor() {
        Catalogo catalogo = mock(Catalogo.class);
        Categoria vuelo = mock(Categoria.class);
        Categoria hotel = mock(Categoria.class);
        when(vuelo.getNombre()).thenReturn("Vuelo");
        when(hotel.getNombre()).thenReturn("Hotel");

        Visitor visitor = mock(Visitor.class);
        when(visitor.visitOcupacionCupos(org.mockito.ArgumentMatchers.any())).thenReturn("ok");

        ReporteOcupacionCupos reporte = new ReporteOcupacionCupos(
                catalogo, List.of(), List.of(vuelo, hotel),
                LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 10));

        assertEquals(2, reporte.getDatos().size());
        assertEquals("ok", reporte.accept(visitor));
        verify(visitor).visitOcupacionCupos(reporte);
    }
}