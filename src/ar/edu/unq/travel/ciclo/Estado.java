package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.servicios.Servicio;

public abstract class Estado {
	
	public void confirmar(Reserva reserva) {
		throw new IllegalStateException("no se puede confirmar la reserva en este estado de la compra");
	}
	public void enCheckin(Reserva reserva) {
		throw new IllegalStateException("no se puede realizar checkin de la reserva en este estado de la compra");
	}
	public void enCurso(Reserva reserva) {
		throw new IllegalStateException("no se puede poner en curso a la reserva en este estado de la compra");
	}
	public void finalizada(Reserva reserva) {
		throw new IllegalStateException("no se puede finalizar la reserva en este estado de la compra");
	}
	public void cancelada(Reserva reserva) {
		throw new IllegalStateException("no se puede cancelar la reserva en este estado de la compra");
	}
	public void agregarServicio(Reserva reserva, Servicio servicio){
		throw new IllegalStateException("solo se pueden agregar servicios al iniciar la reserva");
	}
	public void quitarServicio(Reserva reserva, Servicio servicio){
		throw new IllegalStateException("solo se pueden quitar servicios al iniciar la reserva");
	}
}