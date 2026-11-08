package ar.edu.unq.travel.atributosDinamicos;

public class Booleano extends Tipo {
    private boolean valor;

    public Booleano(String nombre, boolean obligatoriedad, boolean valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    public boolean getValor() {
        return valor;
    }
    
}
