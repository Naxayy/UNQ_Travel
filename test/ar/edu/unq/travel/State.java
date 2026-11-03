package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class State {
	
	@Test
	void estadoInicial() {
		Reserva reserva = new Reserva();
		assertInstanceOf(Pendiente.class , reserva.getEstado());
		//todos los pedidos de estados posterior a confirmado son invalidos
		//solo aca se pueden agregar o quitar servicios
	}


	@Test
	void deInicialAConfirmada(){
		Reserva reserva = new Reserva();
		assertInstanceOf(Pendiente.class, reserva.getEstado());
		reserva.confirmar();
		//todos los pedidos de estados anteriores son invalidos
		//confirmar decrementa o bloquea el cupo disponible
		assertInstanceOf(Confirmada.class, reserva.getEstado());
	}

	
	
	@Test
	void deConfirmadaAEnCheckin() {
		Reserva reserva = new Reserva();
		reserva.confirmar();
		assertInstanceOf(Confirmada.class,reserva.getEstado());
		reserva.enCheckin();
		//todos los pedidos de estados anteriores son invalidos
		//todos los pedidos de estados posterior a checkin son invalidos
		//la cancelacion del checkin implica liberar cupo y reembolso total
		assertInstanceOf(EnCheckin.class, reserva.getEstado());
	}
		
	@Test
	void deCheckinACurso() {
		Reserva reserva = new Reserva();
		reserva.confirmar();
		reserva.enCheckin();
		assertInstanceOf(EnCheckin.class ,reserva.getEstado());
		reserva.enCurso();
		//todos los pedidos de estados anteriores son invalidos
		//La cancelacion de el en curso solo se reembolsa el costo de servicios pero no tasas
		assertInstanceOf(EnCurso.class,  reserva.getEstado());
	}
	

	@Test
	void deCursoAFinalizada() {
		Reserva reserva = new Reserva();
		reserva.confirmar();
		reserva.enCheckin();
		reserva.enCurso();
		assertInstanceOf(EnCurso.class, reserva.getEstado());
		reserva.finalizar();
		//no se puede cancelar ni pasar a otro estado
		assertInstanceOf(Finalizada.class, reserva.getEstado());
	}
	
	

	
	//------cancelados
	
	@Test
	void estadoInicialACancelado() {
		Reserva reserva = new Reserva();
		assertInstanceOf(Pendiente.class , reserva.getEstado());
		reserva.cancelar();
		assertInstanceOf(Cancelada.class, reserva.getEstado());
	}
	


	@Test
	void deConfirmadaACancelado(){
		Reserva reserva = new Reserva();
		reserva.confirmar();
		assertInstanceOf(Confirmada.class,reserva.getEstado());
		reserva.cancelar();
		//confirmar decrementa o bloquea el cupo disponible
		assertInstanceOf(Cancelada.class, reserva.getEstado());
	}
		

	
	@Test
	void deEnCheckinACancelado() {
		Reserva reserva = new Reserva();
		reserva.confirmar();
		reserva.enCheckin();
		assertInstanceOf(EnCheckin.class,reserva.getEstado());
		reserva.cancelar();
		//la cancelacion del checkin implica liberar cupo y reembolso total
		assertInstanceOf(Cancelada.class, reserva.getEstado());
	}
	

	
	@Test
	void deCursoACancelado() {
		Reserva reserva = new Reserva();
		reserva.confirmar();
		reserva.enCheckin();
		reserva.enCurso();
		assertInstanceOf(EnCurso.class, reserva.getEstado());
		reserva.cancelar();
		//La cancelacion de el en curso solo se reembolsa el costo de servicios pero no tasas
		assertInstanceOf(Cancelada.class, reserva.getEstado());
	}
	
}
/*	
	//---------estados invalidos-----
	//Para todo esto hay que crear una clase para las excepciones y
	//cambiar el illegalStateException por la apropiada
	
	@Test
	void invalidoDesdeInicial() {
		//todos los pedidos de estados posterior a confirmado son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkin();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizar();
		});
	}
	
	@Test
	void invalidoDesdeConfirmado(){
		reserva.confirmar();
		//todos los pedidos de estados anteriores son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizar();
		});
	}
		
	@Test
	void invalidoDesdeCheckin() {
		//todos los pedidos de estados anteriores son invalidos
		//todos los pedidos de estados posterior a checkin son invalidos
		reserva.confirmar();
		reserva.checkin();
		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmar();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizar();
		});
	}
	
	@Test
	void invalidoDesdeEnCurso() {
		reserva.confirmar();
		reserva.checkin();
		reserva.enCurso();
		//todos los pedidos de estados anteriores son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkin();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmar();
		});
	}
	@Test
	void invalidoDesdeFinalizado() {
		reserva.confirmar();
		reserva.checkin();
		reserva.enCurso();
		reserva.finalizar();

		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmar();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkin();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.cancelar();
		});
	}

	@Test
	void invalidoDesdeCancelado() {
		reserva.cancelar();
		
		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmar();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkin();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizar();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.cancelar();
		});
		
	}
	

}
*/

