package ar.edu.unq.travel.notificaciones;

public class GeneradorDeVoucher implements Observador {

    public GeneradorDeVoucher(){}

    private Voucher generarVaucher(Double monto){
        return new Voucher(monto);
    } 
   
    @Override
    public void actualizar() {
    }
}