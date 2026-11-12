package ar.edu.unq.travel.notificaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Publicador {
    private List<Observador> observadores = new ArrayList<Observador>();

	public Publicador(){}

	public void notificarAObservadores() {
		for (Observador observador : observadores) {
			observador.actualizar();
		}
	}
	public void agregarObservador(Observador observador) {
		this.observadores.add(observador);
	}
	
	public void eliminarObservador(Observador observador) {
		this.observadores.remove(observador);
	}

}