package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class ReporteServiciosMasReservados implements Element {

    private List<DatoServicio> datos;

    public ReporteServiciosMasReservados(List<Reserva> reservas, LocalDate desde, LocalDate hasta) {
        this.datos = new ArrayList<>();
        this.serviciosReservadosEnPeriodo(reservas, desde, hasta)
                .forEach(servicio -> this.datos.add(new DatoServicio(servicio, reservas, desde, hasta)));
        this.datos.sort((a, b) -> Integer.compare(b.getCantidad(), a.getCantidad()));
    }

    public List<DatoServicio> getDatos() {
        return this.datos;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitServiciosMasReservados(this);
    }

    private Set<Servicio> serviciosReservadosEnPeriodo(List<Reserva> reservas, LocalDate desde, LocalDate hasta) {
        Set<Servicio> resultado = new HashSet<>();
        reservas.stream()
                .filter(reserva -> this.esValidaEnPeriodo(reserva, desde, hasta))
                .forEach(reserva -> resultado.addAll(reserva.getServicios()));
        return resultado;
    }

    private boolean esValidaEnPeriodo(Reserva reserva, LocalDate desde, LocalDate hasta) {
        boolean enPeriodo = !reserva.getFechaFin().isBefore(desde) && !reserva.getFechaInicio().isAfter(hasta);
        return reserva.getEstado().ocupaCupo() && enPeriodo;
    }
}