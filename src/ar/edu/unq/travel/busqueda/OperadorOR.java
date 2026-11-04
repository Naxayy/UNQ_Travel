package ar.edu.unq.travel.busqueda;

import ar.edu.unq.travel.servicios.Servicio;
import java.util.List;

public class OperadorOR extends CriterioComplejo {

    private List<CriterioBusqueda> criterios;

    public OperadorOR(List<CriterioBusqueda> criterios) {
        super(criterios);
    }

    public boolean satisface(Servicio item) {
        return this.criterios.stream().anyMatch(criterio -> criterio.satisface(item));
    }
}
