package ar.edu.unq.travel.traslados;

import ar.edu.unq.travel.Reserva;

public class TrasladoVip extends Traslado {

    private int porcentajeFijo;
    private double cargoBase;

    public TrasladoVip(int porcentajeFijo, double cargoBase){
        this.porcentajeFijo = porcentajeFijo;
        this.cargoBase = cargoBase;    
    }

    @Override
    public double calcularCosto(Reserva reserva){
        return reserva.costoDeServicio() * this.porcentajeFijo + this.cargoBase ;
    }

    protected void setPorcentajeFijo(int porcentaje){
        this.porcentajeFijo = porcentaje;
    }
    protected void cargoBase(double cargoBase){

    }
}
