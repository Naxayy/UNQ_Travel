package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public abstract class Estado {
	
	public void confirmar(Reserva reserva) {
		throw new IllegalStateException("");
	}
	public void enCheckin(Reserva reserva) {
		throw new IllegalStateException("");
	}
	public void enCurso(Reserva reserva) {
		throw new IllegalStateException("");
	}
	public void finalizada(Reserva reserva) {
		throw new IllegalStateException("");
	}
	public void cancelada(Reserva reserva) {
		throw new IllegalStateException("");
	}
	public void agregarServicio(Reserva reserva, Servicio servicio){
		throw new IllegalStateException("");
	}
	
}
