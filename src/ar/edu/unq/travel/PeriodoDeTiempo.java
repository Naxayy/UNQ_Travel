package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.travel.ciclo.SolicitudPendiente;
import ar.edu.unq.travel.notificaciones.Observador;
import ar.edu.unq.travel.pagos.MetodoDePago;
import ar.edu.unq.travel.traslados.Direccion;
import ar.edu.unq.travel.traslados.Traslado;

public class PeriodoDeTiempo {
    private LocalDate fechaInicio;
	private LocalDate fechaFin;

    public PeriodoDeTiempo(LocalDate inicio, LocalDate fin) {
		this.fechaInicio = inicio;
		this.fechaFin = fin;
	}

    public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
}
