package ar.edu.unq.travel.atributosDinamicos;

public class Texto extends Tipo{
    private String valor;

    public Texto(String nombre, boolean obligatoriedad, String valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
