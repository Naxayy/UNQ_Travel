package ar.edu.unq.travel;

public class TrasladoVip extends Traslado {

    private int porcentajeFijo;
    private double cargoBase;

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
