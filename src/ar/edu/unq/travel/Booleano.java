package ar.edu.unq.travel;

public class Booleano extends AtributoDinamico {
    private boolean valor;

    public Booleano(String nombre, boolean obligatoriedad, boolean valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }
    
}
