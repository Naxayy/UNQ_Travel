package ar.edu.unq.travel.busqueda;

import java.util.List;

public abstract class CriterioComplejo implements CriterioBusqueda {
    private List<CriterioBusqueda> criterios;

    public CriterioComplejo(List<CriterioBusqueda> criterios) {
        this.criterios = criterios;
    }

    protected List<CriterioBusqueda> getCriterios() {
        return this.criterios;
    }
}