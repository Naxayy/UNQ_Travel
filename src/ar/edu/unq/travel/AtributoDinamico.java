package ar.edu.unq.travel;

public class AtributoDinamico {
    private String nombre;
    private boolean obligatoriedad;

    public AtributoDinamico(String nombre, boolean obligatoriedad){
        this.nombre = nombre;
        this.obligatoriedad = obligatoriedad;
    }

    public boolean validar(){
        //(a) que todo atributo obligatorio tenga valor
        //(b) que el valor asignado sea consistente con el tipo

        //como ya lo hace java asumo que el valor siempre es true
    }

    //en este caso no hay valor asigando
    public boolean hayValorAsignado(){
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getObligatoriedad(){
        return obligatoriedad;
    }

    public void setObligatoriedad(boolean obligatoriedad) {
        this.obligatoriedad = obligatoriedad;
    }
    
}
