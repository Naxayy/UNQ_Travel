package ar.edu.unq.travel.notificaciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.ciclo.Estado;

public abstract class Observable {
    private List<Observer> observers = new ArrayList<Observer>();

    public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public void agregarObserver(Observer observer) {
		this.observers.add(observer);
	}

	public void eliminarObserver(Observer observer) {
		this.observers.remove(observer);
	}

    protected abstract Estado getEstado();

    protected abstract double costoDeServicioConTasas();

    protected abstract Estado getEstadoAnterior();

    protected abstract double costoDeServicio();
}
