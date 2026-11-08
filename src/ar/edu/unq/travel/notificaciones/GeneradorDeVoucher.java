package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.*;

public class GeneradorDeVoucher implements Observer {
    
    
    private double monto(Reserva reserva){
        if(reserva.getEstadoAnterior() instanceof EnCheckin){
            return reserva.costoDeServicio();
        }else{
            return reserva.costoDeServicioConTasas();
        }
    } 
   
    @Override
    public void update(Reserva observable) {
        if (this.validarEstado(observable)) {
            this.generarVoucher(observable);
        }
    }

    public boolean validarEstado(Reserva observable) {
        return observable.getEstado() instanceof Cancelada;
    }

    private void generarVoucher(Reserva observable) {
        new Voucher(this.monto(observable));
    }

}

//cuando ccreo un paquete tengo que ver si tiene cupo dispo


/*
un usuario tiene una lista de vouchers
*/


/*
opciones: 
    el cliente de clase Usuario tiene un voucher, 
        reserva -> Usuario -> Voucher 
            si yo quiero que un voucher se le sea dado a un usuario por el valor de una reserva particular entonces hago reserva.usuario.voucher

            reserva.generarVoucher()

*/