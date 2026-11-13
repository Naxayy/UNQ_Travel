package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;


public class ObsFidelizacion implements Observador {
    private Reserva reserva;

    public ObsFidelizacion(Reserva reserva){
        this.reserva = reserva;
    }

    @Override
    public void actualizar() {
        Estado estado = reserva.getEstado();
        if(estado.esFidelizador()){
            //cupon de descuento con 5%
        }
    }
}
