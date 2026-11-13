package ar.edu.unq.travel.ciclo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.travel.Reserva;

class EstadoTest {

	//la reserva al hacer cambios de estado invalidos da error  
	//la reserva al hacer cambios de estado validos: 
	// 										cambia de estado 	
	//										modifica el carrito
	//el estado al ser cancelado da un monto para el vaucher
	//que el caucher reciba el monto correcto

	Reserva reserva;
	Estado estado;

	@BeforeEach
	void SetUp(){
		this.reserva = mock(Reserva.class);
		this.estado = new confirmada();
	}

	//testeo estado 
	//que cuando reserva me mande a confirmar yo lance error

	void invalidoDesdeInicial() {
		//todos los pedidos de estados posterior a confirmado son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCheckin();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizar();
		});
	}
}
