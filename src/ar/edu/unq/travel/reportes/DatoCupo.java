package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoCupo {

    private Categoria categoria;
    private Catalogo catalogo;
    private List<Reserva> reservas;
    private LocalDate desde;
    private LocalDate hasta;

    public DatoCupo(Categoria categoria, Catalogo catalogo, List<Reserva> reservas, LocalDate desde, LocalDate hasta) {
        this.categoria = categoria;
        this.catalogo = catalogo;
        this.reservas = reservas;
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getCategoria() {
        return categoria.getNombre();
    }

    public int getLibres() {
        return catalogo.cuposDisponibles(categoria, desde, hasta);
    }

    public int getReservados() {
        int reservados = 0;

        for (Reserva reserva : reservas) {
            reservados += reservadosEnReserva(reserva);
        }

        return reservados;
    }

    public int getTotales() {
        return getLibres() + getReservados();
    }

    private int reservadosEnReserva(Reserva reserva) {
        if (!estaActiva(reserva)) {
            return 0;
        }

        int dias = contarDiasEnPeriodo(reserva);

        if (dias <= 0) {
            return 0;
        }

        return cantidadServiciosDeCategoria(reserva) * reserva.getCantPersonas() * dias;
    }

    private int cantidadServiciosDeCategoria(Reserva reserva) {
        int cantidad = 0;

        for (Servicio servicio : reserva.getServicios()) {
            if (servicio.contieneCategoria(categoria)) {
                cantidad++;
            }
        }

        return cantidad;
    }

    private boolean estaActiva(Reserva reserva) {
        return reserva.getEstado().ocupaCupo();
    }

    private int contarDiasEnPeriodo(Reserva reserva) {
        LocalDate inicio = reserva.getFechaInicio().isAfter(desde) ? reserva.getFechaInicio() : desde;
        LocalDate fin = reserva.getFechaFin().isBefore(hasta) ? reserva.getFechaFin() : hasta;
        int dias = 0;

        for (LocalDate fecha = inicio; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {
            dias++;
        }

        return dias;
    }
}