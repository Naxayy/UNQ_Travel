package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class ObsGeneradorDeVoucher implements Observador {
    private Reserva reserva;

    public ObsGeneradorDeVoucher(Reserva reserva){
        this.reserva = reserva;
    }

    public Voucher generarVoucher(Double monto){
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
}
