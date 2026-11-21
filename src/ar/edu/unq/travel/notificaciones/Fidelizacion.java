package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class Fidelizacion implements Observador {
    private Reserva reserva;

    public Fidelizacion(Reserva reserva){
        this.reserva = reserva;
    }

    @Override
    public void accept(Estado estado) {
        estado.visitarFidelizacion(this);
    }

    public void setReserva(Reserva reserva){
        this.reserva = reserva;
    }

    public Reserva getReserva(){
        return this.reserva;
    }
}
