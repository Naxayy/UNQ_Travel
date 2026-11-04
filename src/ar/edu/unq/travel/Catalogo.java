package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.busqueda.CriterioBusqueda;

public class Catalogo {
    private List<Servicio> items;
    private List<CriterioBusqueda> condiciones;

    public List<Servicio> busqueda() {
        List<Servicio> itemsFiltrados = new ArrayList<Servicio>();

        return itemsFiltrados;
    }

    public void setCondiciones(List<CriterioBusqueda> condiciones) {
        this.condiciones = condiciones;
    }
}
