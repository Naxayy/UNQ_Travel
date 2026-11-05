package ar.edu.unq.travel.busqueda;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioNombre implements CriterioBusqueda {
    private String textoBuscado;

   public CriterioNombre(String textoBuscado) {
        this.textoBuscado = textoBuscado;
    }

    @Override
    public boolean satisface(Servicio item) {
        return item.getNombre().contains(this.textoBuscado);
    }
}
