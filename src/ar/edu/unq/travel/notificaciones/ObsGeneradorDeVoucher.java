package ar.edu.unq.travel.notificaciones;

public class ObsGeneradorDeVoucher implements Observador {

    public ObsGeneradorDeVoucher(){}

    private Voucher generarVaucher(Double monto){
        return new Voucher(monto);
    } 
   
    @Override
    public void actualizar() {
    }
}