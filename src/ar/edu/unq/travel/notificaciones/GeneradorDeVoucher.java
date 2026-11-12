package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.*;

public class GeneradorDeVoucher implements Observador {

    public GeneradorDeVoucher(){}

    private Voucher generarVaucher(Double monto){
        return new Voucher(monto);
    } 
   
    @Override
    public void actualizar() {
    }
}