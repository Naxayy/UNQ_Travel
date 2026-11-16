package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.busqueda.CriterioBusqueda;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.servicios.Servicio;

public class Catalogo {
    private List<Servicio> items;
    private CriterioBusqueda condicion;

    public Catalogo() {
        this.items = new ArrayList<>();
    }

    public List<Servicio> busqueda(CriterioBusqueda condicion) {
        this.setCondicion(condicion);
        return items.stream().filter(item -> condicion.satisface(item)).toList();
    }

    public List<Servicio> getItems() {
        return this.items;
    }

    public void agregarItem(Servicio item) {
        this.items.add(item);
    }

    private void setCondicion(CriterioBusqueda condicion) {
        this.condicion = condicion;
    }

    public int cuposDisponibles(Categoria categoria, LocalDate desde, LocalDate hasta) {
        int libres = 0;

        for (Servicio servicio : getItems()) {
            if (servicio.contieneCategoria(categoria)) {
                libres += servicio.cuposDisponiblesEnPeriodo(desde, hasta);
            }
        }
        return libres;
    }
}
