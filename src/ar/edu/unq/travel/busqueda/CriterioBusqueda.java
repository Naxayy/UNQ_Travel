package ar.edu.unq.travel.busqueda;

import ar.edu.unq.travel.servicios.Servicio;

public interface CriterioBusqueda {
    public boolean satisface(Servicio item);
}
