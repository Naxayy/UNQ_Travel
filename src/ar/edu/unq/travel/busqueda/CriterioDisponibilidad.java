package ar.edu.unq.travel.busqueda;

import java.time.LocalDate;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioDisponibilidad implements CriterioBusqueda {
    private LocalDate fecha;
    private int cantidad;

    public CriterioDisponibilidad(LocalDate fecha, int cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
    }
    
    public boolean satisface(Servicio item) {
        return  item.hayDisponibilidad(fecha, cantidad);
    };
}
