package ar.edu.unq.travel.notificaciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.ciclo.Estado;

public abstract class Observable {
    private List<Observador> observers = new ArrayList<Observador>();

    public void notifyObservers() {
		for (Observador observer : observers) {
			observer.actualizar();
		}
	}

	public void agregarObserver(Observador observer) {
		this.observers.add(observer);
	}

	public void eliminarObserver(Observador observer) {
		this.observers.remove(observer);
	}

    protected abstract Estado getEstado();

    protected abstract double costoDeServicioConTasas();

    protected abstract Estado getEstadoAnterior();

    protected abstract double costoDeServicio();
}
