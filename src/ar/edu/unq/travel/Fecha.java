package ar.edu.unq.travel;
import java.util.Date;

public class Fecha extends Tipo {
    private Date valor;

    public Fecha(String nombre, boolean obligatoriedad, Date valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    public Date getValor() {
        return valor;
    }
}
