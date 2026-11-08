package ar.edu.unq.travel.traslados;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.plataformaTransporte.Direccion;
import ar.edu.unq.travel.plataformaTransporte.PlataformaTransporte;

public class TrasladoEstandar extends Traslado {
    private PlataformaTransporte plataforma;

    public TrasladoEstandar(PlataformaTransporte plataforma){
        this.plataforma = plataforma;
    }
    
    @Override
    public double calcularCosto(Reserva reserva){
        int cantPasajeros = reserva.pasajeros();
        Direccion destino = reserva.direccionDestino();
        return plataforma.estimarTraslado(cantPasajeros, destino);
    }

}