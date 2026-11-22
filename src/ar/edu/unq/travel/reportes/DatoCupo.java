package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.servicios.Servicio;

public class DatoCupo {

    private Categoria categoria;
    private Catalogo catalogo;
    private List<Reserva> reservas;
    private PeriodoDeTiempo periodoDeTiempo;

    public DatoCupo(Categoria categoria, Catalogo catalogo, List<Reserva> reservas, PeriodoDeTiempo periodoDeTiempo) {
        this.categoria = categoria;
        this.catalogo = catalogo;
        this.reservas = reservas;
        this.periodoDeTiempo = periodoDeTiempo;
    }

    public String getCategoria() {
        return categoria.getNombre();
    }

    public int getLibres() {
        return catalogo.cuposDisponibles(categoria, periodoDeTiempo);
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

        return cantidadServiciosDeCategoria(reserva) * reserva.getCantidadPasajeros() * dias;
    }

    private int cantidadServiciosDeCategoria(Reserva reserva) {
        Set<Servicio> individuales = new HashSet<>();

        reserva.getServicios().forEach(servicio -> servicio.recolectarServiciosIndividuales(individuales));
        
        return individuales.stream()
                .mapToInt(servicio -> servicio.contieneCategoria(this.categoria) ? 1 : 0)
                .sum();
    }

    private boolean estaActiva(Reserva reserva) {
        return reserva.getEstado().ocupaCupo();
    }

    private int contarDiasEnPeriodo(Reserva reserva) {
        LocalDate inicio = this.laQueEstaDespues(reserva.fechaDeInicio(), this.periodoDeTiempo.getFechaInicio());
                            //reserva.getFechaInicio().isAfter(desde) ? reserva.getFechaInicio() : desde;
        LocalDate fin = this.laQueEstaAntes(reserva.fechaFin(), this.periodoDeTiempo.getFechaFin());
                        //reserva.getFechaFin().isBefore(hasta) ? reserva.getFechaFin()   : hasta;

        int dias = 0;

        for (LocalDate fecha = inicio; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {
            dias++;
        }

        return dias;
    }
    
    private LocalDate laQueEstaDespues(LocalDate fechaReserva, LocalDate desde){
        if (fechaReserva.isAfter(desde)){
            return fechaReserva;            
        } else {
            return desde;
        }
    }
}