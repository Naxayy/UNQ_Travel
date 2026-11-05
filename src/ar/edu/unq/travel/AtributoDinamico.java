package ar.edu.unq.travel;

public class AtributoDinamico {
    private String nombre;
    private boolean obligatoriedad;

    public AtributoDinamico(String nombre, boolean obligatoriedad){
        this.nombre = nombre;
        this.obligatoriedad = obligatoriedad;
    }

    public void validar(){
        //(a) que todo atributo obligatorio tenga valor
        //(b) que el valor asignado sea consistente con el tipo

        //si es obligatorio y se instancia desde aca da error, ya que atributoDinamico no tiene valor
        if(this.esObligatorio()){
            throw new IllegalStateException("Es obligatorio y no tiene valor");
        }
    }

    private boolean esObligatorio(){
        return this.getObligatoriedad();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getObligatoriedad(){
        return obligatoriedad;
    }

    public void setObligatoriedad(boolean obligatoriedad) {
        this.obligatoriedad = obligatoriedad;
    }
    
}
