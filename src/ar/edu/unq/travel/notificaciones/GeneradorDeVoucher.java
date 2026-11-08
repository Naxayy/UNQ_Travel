package ar.edu.unq.travel.notificaciones;

import ar.edu.unq.travel.ciclo.*;

public class GeneradorDeVoucher implements Observer {
    
    
    private double monto(Observable observable){
        if(observable.getEstadoAnterior() instanceof EnCheckin){
            return observable.costoDeServicio();
        }else{
            return observable.costoDeServicioConTasas();
        }
    } 
   
    @Override
    public void update(Observable observable) {
        if (this.validarEstado(observable)) {
            this.generarVoucher(observable);
        }
    }

    public boolean validarEstado(Observable observable) {
        return observable.getEstado() instanceof Cancelada;
    }

    private void generarVoucher(Observable observable) {
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