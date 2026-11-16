package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoServicio {

    private Servicio servicio;
    private List<Reserva> reservas;
    private LocalDate desde;
    private LocalDate hasta;

    public DatoServicio(Servicio servicio, List<Reserva> reservas, LocalDate desde, LocalDate hasta) {
        this.servicio = servicio;
        this.reservas = reservas;
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getNombre() {
        return this.servicio.getNombre();
    }

    public int getCantidad() {
        return this.reservas.stream()
                .mapToInt(this::cantidadEnReserva)
                .sum();
    }

    public double getPrecioPromedio() {
        int cantidad = this.getCantidad();
        if (cantidad == 0) {
            return 0.0;
        }
        double recaudado = this.reservas.stream()
                .mapToDouble(reserva -> this.cantidadEnReserva(reserva) * this.servicio.precioFinal())
                .sum();
        return recaudado / cantidad;
    }

    private int cantidadEnReserva(Reserva reserva) {
        if (!reserva.getEstado().ocupaCupo() || !this.estaEnPeriodo(reserva)) {
            return 0;
        }
        return this.aparicionesEnReserva(reserva) * reserva.getCantPersonas();
    }

    private int aparicionesEnReserva(Reserva reserva) {
        return reserva.getServicios().stream()
                .mapToInt(s -> s.equals(this.servicio) ? 1 : 0)
                .sum();
    }

    private boolean estaEnPeriodo(Reserva reserva) {
        return !reserva.getFechaFin().isBefore(this.desde)
            && !reserva.getFechaInicio().isAfter(this.hasta);
    }
}