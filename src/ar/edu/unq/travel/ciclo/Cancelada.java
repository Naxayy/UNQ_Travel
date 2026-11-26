package ar.edu.unq.travel.ciclo;

import ar.edu.unq.travel.notificaciones.Fidelizacion;
import ar.edu.unq.travel.notificaciones.GeneradorDeVoucher;

public class Cancelada extends Estado {
    public Cancelada() {}

    @Override
	public void visitarGeneradorDeVoucher(GeneradorDeVoucher generador){
		generador.generarVoucher();
	}
}
