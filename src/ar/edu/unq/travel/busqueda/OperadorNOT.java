package ar.edu.unq.travel.busqueda;

import java.util.List;
import ar.edu.unq.travel.servicios.Servicio;

public class OperadorNOT extends CriterioComplejo {
    
    private List<CriterioBusqueda> criterios;

    public OperadorNOT(List<CriterioBusqueda> criterios) {
        super(criterios);
    }

    public boolean satisface(Servicio item) {
        return this.criterios.stream().noneMatch(criterio -> criterio.satisface(item));
    }
}
