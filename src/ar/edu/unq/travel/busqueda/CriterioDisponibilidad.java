package ar.edu.unq.travel.busqueda;

import java.time.LocalDate;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioDisponibilidad implements CriterioBusqueda {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cantidad;

    public CriterioDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, int cantidad) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
    }
    
    @Override
    public boolean satisface(Servicio item) {
        return  item.hayDisponibilidad(fechaInicio, fechaFin, cantidad);
    }
}
