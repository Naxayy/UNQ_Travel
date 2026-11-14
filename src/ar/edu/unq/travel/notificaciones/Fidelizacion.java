package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class Fidelizacion implements Observador {
    private Reserva reserva;

    public Fidelizacion(Reserva reserva){
        this.reserva = reserva;
    }

    @Override
    public void actualizar() {
        Estado estado = reserva.getEstado();
        if(estado.esFidelizador()){
            //cupon de descuento con 5%
        }
    }

    public void setReserva(Reserva reserva){
        this.reserva = reserva;
    }

    public Reserva getReserva(){
        return this.reserva;
    }
}
