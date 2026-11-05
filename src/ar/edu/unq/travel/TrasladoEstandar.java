package ar.edu.unq.travel;

public class TrasladoEstandar extends Traslado {
    private PlataformaTransporte plataforma;

    public TrasladoEstandar(PlataformaTransporte plataforma){
        this.plataforma = plataforma;
    }
    
    @Override
    public double calcularCosto(Reserva reserva){
        return plataforma.estimarTraslado( reserva.pasajeros(), reserva.direccionDestino());
    }
}