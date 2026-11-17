package ar.edu.unq.travel.busqueda;

import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;

public class OperadorNOT extends CriterioComplejo {

    public OperadorNOT(List<CriterioBusqueda> criterios) {
        super(criterios);
    }

    @Override
    public boolean satisface(Servicio item) {
        return this.getCriterios().stream().noneMatch(criterio -> criterio.satisface(item));
    }
}