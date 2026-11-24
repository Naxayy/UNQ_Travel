package ar.edu.unq.travel;

import java.time.LocalDate;

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
