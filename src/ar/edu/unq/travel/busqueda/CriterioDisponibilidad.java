package ar.edu.unq.travel.busqueda;

import java.time.LocalDate;

import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.servicios.Servicio;

public class CriterioDisponibilidad implements CriterioBusqueda {
    private PeriodoDeTiempo periodo;
    private int cantidad;

    public CriterioDisponibilidad(PeriodoDeTiempo periodo, int cantidad) {
        this.periodo = periodo;
        this.cantidad = cantidad;
    }
    
    @Override
    public boolean satisface(Servicio item) {
        return  item.hayDisponibilidad(periodo, cantidad);
    }
}
