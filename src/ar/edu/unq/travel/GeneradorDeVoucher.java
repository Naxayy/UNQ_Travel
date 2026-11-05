package ar.edu.unq.travel;

public class GeneradorDeVoucher implements Observer {
    
    @Override
    public void update(Reserva observable) {
        if (this.validarEstado(observable)) {
            System.out.println("Se genero un voucher y comprobante fiscal");
        }
    }

    public boolean validarEstado(Reserva observable) {
        return observable.getEstado() instanceof Finalizada;
    }
    

}

