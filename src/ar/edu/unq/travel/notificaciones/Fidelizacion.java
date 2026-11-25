package ar.edu.unq.travel.notificaciones;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.ciclo.Estado;

public class Fidelizacion implements Observador {

    public Fidelizacion(){}

    @Override
    public void accept(Estado estado) {
        estado.visitarFidelizacion(this);
    }

    public void generarCupo(){
        //logica del cupon
    }
}
