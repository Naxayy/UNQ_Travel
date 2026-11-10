package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.*;

public class GeneradorDeVoucher implements Observador {

    private Voucher generasrVaucher(Double monto){
        return new Voucher(monto);
    } 
   
    @Override
    public void update() {
    }
}