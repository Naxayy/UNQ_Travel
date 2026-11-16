package ar.edu.unq.travel.notificaciones;

import java.util.List;

public class Publicador {
    private List<Observador> observadores;

	public Publicador(List<Observador> observadores) {
		this.observadores = observadores;
	}

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