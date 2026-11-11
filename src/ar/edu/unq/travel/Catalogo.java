package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;
import ar.edu.unq.travel.busqueda.CriterioBusqueda;

public class Catalogo {
    private List<Servicio> items;
    private CriterioBusqueda condicion;

    public void Catalogo() {
        this.items = new ArrayList<>();
    };

    public List<Servicio> busqueda() {
        return items.stream().filter(item -> condicion.satisface(item)).toList();
    }

    public void agregarItem(Servicio item) {
        this.items.add(item);
    }

    public void setCondicion(CriterioBusqueda condicion) {
        this.condicion = condicion;
    }
}
