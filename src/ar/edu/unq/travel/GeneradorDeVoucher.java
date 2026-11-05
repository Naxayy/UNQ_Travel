package ar.edu.unq.travel;

public class GeneradorDeVoucher implements Observer {
    
    @Override
    public void update(Reserva observable) {
        if (this.validarEstado(observable)) {
            System.out.println("Se genero un voucher y comprobante fiscal");
        }
    }

    public boolean validarEstado(Reserva observable) {
        return observable.getEstado() instanceof Cancelada;
    }


}


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