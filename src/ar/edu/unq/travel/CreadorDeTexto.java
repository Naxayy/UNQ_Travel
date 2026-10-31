package ar.edu.unq.travel;

public class CreadorDeTexto extends AtributoDinamico{

	public CreadorDeTexto(String nombre, boolean obligatorio) {
		super(nombre, obligatorio);
	}

	@Override
	public Tipo crearTipo() {
		return new Texto();
	}

}
