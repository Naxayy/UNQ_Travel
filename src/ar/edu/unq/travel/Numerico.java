package ar.edu.unq.travel;

public class Numerico implements Tipo {
	
	@Override
    public boolean esValido(Numerico valor) {
        return valor != null;
    }

	@Override
	public boolean esValido(Tipo valor) {
		// TODO Auto-generated method stub
		return false;
	}

}
