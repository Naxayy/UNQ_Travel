package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;

public class ReporteOcupacionCuposTest {

    private Catalogo catalogo;
    private PeriodoDeTiempo periodo;

    @BeforeEach
    public void setUp() {
        catalogo = mock(Catalogo.class);
        periodo = new PeriodoDeTiempo(LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 30));
    }

    @Test
    public void testGetDatosCreaUnDatoCupoPorCadaCategoria() {
        Categoria alojamiento = mock(Categoria.class);
        when(alojamiento.getNombre()).thenReturn("Alojamiento");
        when(catalogo.cuposDisponibles(alojamiento, periodo)).thenReturn(12);

        Categoria transporte = mock(Categoria.class);
        when(transporte.getNombre()).thenReturn("Transporte");
        when(catalogo.cuposDisponibles(transporte, periodo)).thenReturn(8);

        List<Reserva> reservas = List.of();
        ReporteOcupacionCupos reporte = new ReporteOcupacionCupos(
                catalogo, reservas, List.of(alojamiento, transporte), periodo);

        List<DatoCupo> datos = reporte.getDatos();

        assertEquals(2, datos.size());
        assertEquals("Alojamiento", datos.get(0).getCategoria());
        assertEquals(12, datos.get(0).getLibres());
        assertEquals("Transporte", datos.get(1).getCategoria());
        assertEquals(8, datos.get(1).getLibres());
    }

    @Test
    public void testSinCategoriasGeneraListaVacia() {
        ReporteOcupacionCupos reporte = new ReporteOcupacionCupos(
                catalogo, List.of(), List.of(), periodo);

        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testAcceptDelegaEnVisitor() {
        Visitor visitor = mock(Visitor.class);
        ReporteOcupacionCupos reporte = new ReporteOcupacionCupos(
                catalogo, List.of(), List.of(), periodo);
        when(visitor.visitOcupacionCupos(reporte)).thenReturn("reporte-cupos");

        String resultado = reporte.accept(visitor);

        assertEquals("reporte-cupos", resultado);
        verify(visitor).visitOcupacionCupos(reporte);
    }
}