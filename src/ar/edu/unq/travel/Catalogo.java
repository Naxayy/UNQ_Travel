package ar.edu.unq.travel;

import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.busqueda.CriterioBusqueda;

public class Catalogo {
    private List<Servicio> items;
    private CriterioBusqueda condicion;

    public List<Servicio> busqueda() {
        return items.stream().filter(item -> condicion.satisface(item)).toList();
    }
}
