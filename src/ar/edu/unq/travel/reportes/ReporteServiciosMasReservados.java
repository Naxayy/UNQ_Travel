package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class ReporteServiciosMasReservados implements Element {
    private List<DatoServicio> datos;

    public ReporteServiciosMasReservados(List<Reserva> reservas, PeriodoDeTiempo periodo) {
        this.datos = new ArrayList<>();

        this.serviciosReservadosEnPeriodo(reservas, periodo)
                .forEach(servicio -> this.datos.add(new DatoServicio(servicio, reservas, periodo)));
                
        this.datos.sort((a, b) -> Integer.compare(b.getCantidad(), a.getCantidad()));
    }

    public List<DatoServicio> getDatos() {
        return this.datos;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitServiciosMasReservados(this);
    }

    private Set<Servicio> serviciosReservadosEnPeriodo(List<Reserva> reservas, PeriodoDeTiempo periodo) {
        Set<Servicio> resultado = new HashSet<>();
        reservas.stream()
                .filter(reserva -> this.esValidaEnPeriodo(reserva, periodo))
                .forEach(reserva -> resultado.addAll(reserva.getServicios()));
        return resultado;
    }

    private boolean esValidaEnPeriodo(Reserva reserva, PeriodoDeTiempo periodo) {
        boolean enPeriodo = !periodoDeReserva(reserva).getFechaFin().isBefore(periodo.getFechaInicio()) && !periodoDeReserva(reserva).getFechaInicio().isAfter(periodo.getFechaFin());
        return reserva.getEstado().ocupaCupo() && enPeriodo;
    }

    private PeriodoDeTiempo periodoDeReserva(Reserva reserva) {
        return reserva.getPeridoDeTiempo();
    }
}