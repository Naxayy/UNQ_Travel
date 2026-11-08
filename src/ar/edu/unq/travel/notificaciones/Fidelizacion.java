package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.ciclo.Cancelada;

public class Fidelizacion implements Observer {

    @Override
    public void update(Observable observable) {
        if (this.validarEstado(observable)) {
            System.out.println("Se ha cancelado la reserva, se le otorgara un descuento del 5%");
        }
    }

    public boolean validarEstado(Observable observable) {
        return observable.getEstado() instanceof Cancelada;
    }
}
