package ar.edu.unq.travel;

public abstract class AtributoDinamico {
	private String nombre;
	private int obligatoriedad;
	private Tipo tipo;
	
	public abstract void crearTipo();
	
	//como lo hacemos?
	public void validar();
}
