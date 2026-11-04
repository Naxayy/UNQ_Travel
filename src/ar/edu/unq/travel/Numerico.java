package ar.edu.unq.travel;

public class Numerico extends Tipo{
    private int valor;

    public Numerico(String nombre, boolean obligatoriedad, int valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
