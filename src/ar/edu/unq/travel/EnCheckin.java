package ar.edu.unq.travel;

public class EnCheckin extends Estado {

	@Override
	public void enCurso(Reserva reserva) {
		reserva.cambiarAEstado(new EnCurso());
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.cambiarAEstado(new Cancelada());
		reserva.cancelarCarrito();
		//private int monto = reserva.costos() + reserva.tasas()
		//reserva.nuevoVoucher(monto);
		//necesito generar el voucher y usar reserva.costoDeServicio y reserva.tasasAdministrativas
	}

}
