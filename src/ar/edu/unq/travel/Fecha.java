package ar.edu.unq.travel;
import java.util.Date;

public class Fecha extends AtributoDinamico {
    private Date valor;

    public Fecha(String nombre, boolean obligatoriedad, Date valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    //En este caso no hace nada, ya que por mas que sea obligatorio o no siepmre tiene valor
    @Override 
    public void validar() {}
}
