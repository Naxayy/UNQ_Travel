package ar.edu.unq.travel;

public class TrasladoVip extends Traslado {

    private int porcentajeFijo;
    private double cargoBase;

    public TrasladoVip(int porcentajeFijo, double cargoBase){
        this.porcentajeFijo = porcentajeFijo;
        this.cargoBase = cargoBase;
        //Preguntar si el constructor se define como this.porcentajeFijo = porcentajeFijo o si es this.setPorcentajeFijo(porcentajeFija)
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
