package ar.edu.unq.travel;

public class Texto extends AtributoDinamico{
    private String valor;

    public Texto(String nombre, boolean obligatoriedad, String valor){
        super(nombre, obligatoriedad);
        this.valor = valor;
    }

    @Override
    public void validar() {}
}
