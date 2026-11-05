package ar.edu.unq.travel.busqueda;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioPrecioMaximo implements CriterioBusqueda {
    private double precioMaximo;

    public CriterioPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    @Override
    public boolean satisface(Servicio item) {
        return item.precioFinal() <= this.precioMaximo;
    }
    
}
