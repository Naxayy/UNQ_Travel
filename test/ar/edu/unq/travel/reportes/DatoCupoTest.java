package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.ciclo.Estado;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoCupoTest {

    private Categoria alojamiento;
    private Catalogo catalogo;
    private PeriodoDeTiempo periodo;

    @BeforeEach
    public void setUp() {
        alojamiento = mock(Categoria.class);
        when(alojamiento.getNombre()).thenReturn("Alojamiento");

        catalogo = mock(Catalogo.class);
        periodo = new PeriodoDeTiempo(LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 30));

        when(catalogo.cuposDisponibles(alojamiento, periodo)).thenReturn(10);
    }

    private Servicio mockServicioIndividual(boolean perteneceACategoria) {
        Servicio servicio = mock(Servicio.class);
        when(servicio.contieneCategoria(alojamiento)).thenReturn(perteneceACategoria);
        doAnswer(invocation -> {
            Set<Servicio> individuales = invocation.getArgument(0);
            individuales.add(servicio);
            return null;
        }).when(servicio).recolectarServiciosIndividuales(any());
        return servicio;
    }

    @Test
    public void testGetCategoriaYGetLibres() {
        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(), periodo);

        assertEquals("Alojamiento", dato.getCategoria());
        assertEquals(10, dato.getLibres());
    }

    @Test
    public void testGetReservadosYGetTotalesConReservaActivaEnPeriodo() {
        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Servicio hotel = mockServicioIndividual(true);
        Servicio traslado = mockServicioIndividual(false);

        // Del 05/11 al 07/11 inclusive = 3 días * 2 pasajeros * 1 servicio de categoría = 6
        Reserva reserva = mock(Reserva.class);
        when(reserva.getEstado()).thenReturn(activa);
        when(reserva.fechaDeInicio()).thenReturn(LocalDate.of(2026, 11, 5));
        when(reserva.fechaFin()).thenReturn(LocalDate.of(2026, 11, 7));
        when(reserva.getCantidadPasajeros()).thenReturn(2);
        when(reserva.getServicios()).thenReturn(List.of(hotel, traslado));

        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(reserva), periodo);

        assertEquals(6, dato.getReservados());
        assertEquals(16, dato.getTotales());
    }

    @Test
    public void testRecortaDiasCuandoLaReservaSuperponeParcialmenteElPeriodo() {
        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Servicio hotel = mockServicioIndividual(true);

        // Empieza el 30/10 y termina el 02/11 -> solo cuenta 01/11 y 02/11 (2 días)
        Reserva reserva = mock(Reserva.class);
        when(reserva.getEstado()).thenReturn(activa);
        when(reserva.fechaDeInicio()).thenReturn(LocalDate.of(2026, 10, 30));
        when(reserva.fechaFin()).thenReturn(LocalDate.of(2026, 11, 2));
        when(reserva.getCantidadPasajeros()).thenReturn(3);
        when(reserva.getServicios()).thenReturn(List.of(hotel));

        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(reserva), periodo);

        assertEquals(6, dato.getReservados());
    }

    @Test
    public void testIgnoraReservasInactivasYFueraDePeriodo() {
        Estado inactiva = mock(Estado.class);
        when(inactiva.ocupaCupo()).thenReturn(false);

        Estado activa = mock(Estado.class);
        when(activa.ocupaCupo()).thenReturn(true);

        Servicio hotel = mockServicioIndividual(true);

        Reserva cancelada = mock(Reserva.class);
        when(cancelada.getEstado()).thenReturn(inactiva);

        Reserva fueraDeFecha = mock(Reserva.class);
        when(fueraDeFecha.getEstado()).thenReturn(activa);
        when(fueraDeFecha.fechaDeInicio()).thenReturn(LocalDate.of(2026, 12, 1));
        when(fueraDeFecha.fechaFin()).thenReturn(LocalDate.of(2026, 12, 5));
        when(fueraDeFecha.getCantidadPasajeros()).thenReturn(2);
        when(fueraDeFecha.getServicios()).thenReturn(List.of(hotel));

        DatoCupo dato = new DatoCupo(alojamiento, catalogo, List.of(cancelada, fueraDeFecha), periodo);

        assertEquals(0, dato.getReservados());
        assertEquals(10, dato.getTotales());
    }
}