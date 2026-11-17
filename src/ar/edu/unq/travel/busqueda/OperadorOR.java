package ar.edu.unq.travel.busqueda;

import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;

public class OperadorOR extends CriterioComplejo {

    public OperadorOR(List<CriterioBusqueda> criterios) {
        super(criterios);
    }

    @Override
    public boolean satisface(Servicio item) {
        return this.getCriterios().stream().anyMatch(criterio -> criterio.satisface(item));
    }
}