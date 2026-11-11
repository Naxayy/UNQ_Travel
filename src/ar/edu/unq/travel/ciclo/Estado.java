package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public abstract class Estado {
	
	public void confirmar() {
		throw new IllegalStateException("no se puede iniciar la resera en este estado de la compra");
	}
	public void enCheckin() {
		throw new IllegalStateException("no se puede realizar checkin la resera en este estado de la compra");
	}
	public void enCurso() {
		throw new IllegalStateException("El viaje ya esta en ejecución");
	}
	public void finalizada() {
		throw new IllegalStateException("no se puede finalizar la resera en este estado de la compra");
	}
	public void cancelada() {
		throw new IllegalStateException("no se puede cancelar la resera en este estado de la compra");
	}
	public void agregarServicio(Servicio servicio){
		throw new IllegalStateException("solo se pueden agregar servicios al iniciar la reserva");
	}
	public void quitarServicio(Servicio servicio){
		throw new IllegalStateException("solo se pueden quitar servicios al iniciar la reserva");
	}
}