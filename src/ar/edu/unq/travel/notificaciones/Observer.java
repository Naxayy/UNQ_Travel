package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.Reserva;

public interface Observer {
    public void update(Reserva observable);
}
