package ar.edu.unq.travel.traslados;

import ar.edu.unq.travel.Reserva;

public class TrasladoEstandar extends Traslado {
    private PlataformaTransporte plataforma;

    public TrasladoEstandar(PlataformaTransporte plataforma){
        this.plataforma = plataforma;
    }
    
    @Override
    public double calcularCosto(Reserva reserva){
        int cantPasajeros = reserva.getCantidadPasajeros();
        Direccion destino = reserva.getDireccion();
        return plataforma.estimarTraslado(cantPasajeros, destino);
    }

}