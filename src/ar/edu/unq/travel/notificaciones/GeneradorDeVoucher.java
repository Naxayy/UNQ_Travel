package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class GeneradorDeVoucher implements Observador {
    private Reserva reserva;

    public GeneradorDeVoucher(Reserva reserva){
        this.reserva = reserva;
    }

    private Voucher generarVoucher(Double monto){
        return new Voucher(monto);
    } 
   
    @Override
    public void actualizar() {
        Estado estado = reserva.getEstado();
        if (estado.esGeneradorDeVoucher()){
            Estado estadoAnterior = reserva.getEstadoAnterior();
            double valor = estadoAnterior.montoVoucher(reserva);
            this.generarVoucher(valor);
        }
    }

    public void setReserva(Reserva reserva){
        this.reserva = reserva;
    }

    public Reserva getReserva(){
        return this.reserva;
    }
}
