package ar.edu.unq.travel;

public abstract class Tipo extends AtributoDinamico {

    protected  Tipo(String nombre, boolean obligatoriedad){
        super(nombre, obligatoriedad);
    }

    //En este caso no hace nada, ya que por mas que sea obligatorio o no siepmre tiene valor
    @Override
    public void validar() {}
}
