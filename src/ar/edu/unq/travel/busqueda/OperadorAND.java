package ar.edu.unq.travel.busqueda;

import java.util.List;
import ar.edu.unq.travel.servicios.Servicio;

public class OperadorAND extends CriterioComplejo {

    private List<CriterioBusqueda> criterios;

    public OperadorAND(List<CriterioBusqueda> criterios) {
        super(criterios);
    }
    
    public boolean satisface(Servicio item) {
        return this.criterios.stream().allMatch(criterio -> criterio.satisface(item));
    }
}
