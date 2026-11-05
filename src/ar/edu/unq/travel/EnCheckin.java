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

/*
un usuario tiene una lista de vouchers
*/


/*
opciones: 
    el cliente de clase Usuario tiene un voucher, 
        reserva -> Usuario -> Voucher 
            si yo quiero que un voucher se le sea dado a un usuario por el valor de una reserva particular entonces hago reserva.usuario.voucher

            reserva.generarVoucher()

*/