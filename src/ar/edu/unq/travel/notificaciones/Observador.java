package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.ciclo.Estado;

public interface Observador {

    public void accept(Estado estado);
}
