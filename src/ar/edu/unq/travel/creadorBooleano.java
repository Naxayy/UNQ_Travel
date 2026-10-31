package ar.edu.unq.travel;

public class creadorBooleano extends AtributoDinamico{

	public creadorBooleano(String nombre, boolean obligatorio) {
		super(nombre, obligatorio);
	}

	@Override
	public Tipo crearTipo() {
		return new Booleano();
	}

}
