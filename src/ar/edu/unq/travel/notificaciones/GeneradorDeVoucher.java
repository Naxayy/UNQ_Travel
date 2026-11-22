package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class GeneradorDeVoucher implements Observador {
    private Reserva reserva;

    public GeneradorDeVoucher(Reserva reserva){
        this.reserva = reserva;
    }

    public Voucher generarVoucher(){
        return new Voucher(this.reserva.getEstadoAnterior().monto(reserva));
    } 
   
    @Override
    public void accept(Estado estado) {
        estado.visitarGeneradorDeVoucher(this);
    }

    public void setReserva(Reserva reserva){
        this.reserva = reserva;
    }

    public Reserva getReserva(){
        return this.reserva;
    }
}
