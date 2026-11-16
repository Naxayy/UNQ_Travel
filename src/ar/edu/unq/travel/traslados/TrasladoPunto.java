package ar.edu.unq.travel.traslados;

import ar.edu.unq.travel.Reserva;

public class TrasladoPunto extends Traslado {

    public TrasladoPunto(){}

    @Override
    public double calcularCosto(Reserva reserva){
        return 0;
    }
}
