package ar.edu.unq.travel.busqueda;

import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;

public class OperadorAND extends CriterioComplejo {

    public OperadorAND(List<CriterioBusqueda> criterios) {
        super(criterios);
    }

    @Override
    public boolean satisface(Servicio item) {
        return this.getCriterios().stream().allMatch(criterio -> criterio.satisface(item));
    }
}