package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Carrito;
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
        Carrito carrito = reserva.getCarrito();
        double valor = reserva.costoDeServicioConTasas();
            this.generarVoucher(valor);
        }
    }
}