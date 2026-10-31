package ar.edu.unq.travel;

public abstract class AtributoDinamico {
	private String nombre;
	private boolean obligatoriedad;
	private Tipo tipo;
	//private .. valor;
	
	public AtributoDinamico(String nombre, boolean obligatorio) {
        this.nombre = nombre;
        this.obligatoriedad = obligatorio;
    }
	
	public void validar() {
		Tipo at = crearTipo();
		// at.validar(valor)
	}
	
	public abstract Tipo crearTipo();

}
