package ar.edu.unq.travel.reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoCupoTest {

    private Categoria alojamiento;
    private Catalogo catalogo;
    private LocalDate desde;
    private LocalDate hasta;

    @BeforeEach
    public void setUp() {
        alojamiento = mock(Categoria.class);
        when(alojamiento.getNombre()).thenReturn("Alojamiento");
        catalogo = mock(Catalogo.class);
        desde = LocalDate.of(2026, 11, 1);
        hasta = LocalDate.of(2026, 11, 10);
    }

    @Test
    public void testGetCategoriaYLibresDelegaEnCategoriaYCatalogo() {
        when(catalogo.cuposDisponibles(alojamiento, desde, hasta)).thenReturn(15);
        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(), desde, hasta);

        assertEquals("Alojamiento", dato.getCategoria());
        assertEquals(15, dato.getLibres());
    }

    @Test
    public void testCalculaReservadosYTotalesConSolapamientoDeFechas() {
        Servicio hotel = mock(Servicio.class);
        when(hotel.contieneCategoria(alojamiento)).thenReturn(true);
        doAnswer(invocation -> {
            Set<Servicio> set = invocation.getArgument(0);
            set.add(hotel);
            return null;
        }).when(hotel).recolectarServiciosIndividuales(anySet());

        Estado confirmada = mock(Estado.class);
        when(confirmada.ocupaCupo()).thenReturn(true);

        // Reserva del 8 al 12 de noviembre: solo 3 días (8, 9 y 10) caen dentro de [desde, hasta]
        Reserva reserva = mock(Reserva.class);
        when(reserva.getEstado()).thenReturn(confirmada);
        when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2026, 11, 8));
        when(reserva.getFechaFin()).thenReturn(LocalDate.of(2026, 11, 12));
        when(reserva.getCantPersonas()).thenReturn(2);
        when(reserva.getServicios()).thenReturn(List.of(hotel));

        when(catalogo.cuposDisponibles(alojamiento, desde, hasta)).thenReturn(10);

        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(reserva), desde, hasta);

        // 1 servicio * 2 personas * 3 días = 6 reservados
        assertEquals(6, dato.getReservados());
        assertEquals(16, dato.getTotales());
    }

    @Test
    public void testIgnoraReservasQueNoOcupanCupoOFueraDePeriodo() {
        Estado cancelada = mock(Estado.class);
        when(cancelada.ocupaCupo()).thenReturn(false);

        Reserva reservaCancelada = mock(Reserva.class);
        when(reservaCancelada.getEstado()).thenReturn(cancelada);

        Estado confirmada = mock(Estado.class);
        when(confirmada.ocupaCupo()).thenReturn(true);

        Reserva reservaFueraDeFecha = mock(Reserva.class);
        when(reservaFueraDeFecha.getEstado()).thenReturn(confirmada);
        when(reservaFueraDeFecha.getFechaInicio()).thenReturn(LocalDate.of(2026, 12, 1));
        when(reservaFueraDeFecha.getFechaFin()).thenReturn(LocalDate.of(2026, 12, 5));

        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(reservaCancelada, reservaFueraDeFecha), desde, hasta);

        assertEquals(0, dato.getReservados());
    }
}