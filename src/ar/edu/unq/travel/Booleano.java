package ar.edu.unq.travel;

public class Booleano extends AtributoDinamico {
    private boolean valor;

    public Booleano(String nombre, boolean obligatoriedad, boolean valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    //En este caso no hace nada, ya que por mas que sea obligatorio o no siepmre tiene valor
    @Override 
    public void validar() {}
    
}
