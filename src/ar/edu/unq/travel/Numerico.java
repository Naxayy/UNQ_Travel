package ar.edu.unq.travel;

public class Numerico extends AtributoDinamico{
    private int valor;

    public Numerico(String nombre, boolean obligatoriedad, int valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    //En este caso no hace nada, ya que por mas que sea obligatorio o no siepmre tiene valor
    @Override 
    public void validar() {}
}
