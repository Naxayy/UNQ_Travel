package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class State {
	private Reserva reserva;
	
	@BeforeEach
	
	void setUp() {
		reserva = new Reserva();
	}

	@Test
	void estadoInicial() {
		assertEquals("Pendiente" , reserva.getEstado().getNombreEstado());
		//todos los pedidos de estados posterior a confirmado son invalidos
		//solo aca se pueden agregar o quitar servicios
	}
	
	@Test
	void deInicialAConfirmada(){
		assertEquals("Pendiente",reserva.getEstado().getNombreEstado());
		reserva.confirmar();
		//todos los pedidos de estados anteriores son invalidos
		//confirmar decrementa o bloquea el cupo disponible
		assertEquals("Confirmada", reserva.getEstado().getNombreEstado());
	}
	
		
	@Test
	void deConfirmadaAEnCheckin() {
		reserva.confirmar();
		assertEquals("Confirmada",reserva.getEstado().getNombreEstado());
		reserva.checkin();
		//todos los pedidos de estados anteriores son invalidos
		//todos los pedidos de estados posterior a checkin son invalidos
		//la cancelacion del checkin implica liberar cupo y reembolso total
		assertEquals("Checkin", reserva.getEstado().getNombreEstado());
	}
	
	@Test
	void deCheckinACurso() {
		reserva.confirmar();
		reserva.checkin();
		assertEquals("Checkin",reserva.getEstado().getNombreEstado());
		reserva.enCurso();
		//todos los pedidos de estados anteriores son invalidos
		//La cancelacion de el en curso solo se reembolsa el costo de servicios pero no tasas
		assertEquals("En curso",  reserva.getEstado().getNombreEstado());
	}
	
	@Test
	void deCursoAFinalizada() {
		reserva.confirmar();
		reserva.checkin();
		reserva.enCurso();
		assertEquals("En curso", reserva.getEstado().getNombreEstado());
		reserva.finalizar();
		//no se puede cancelar ni pasar a otro estado
		assertEquals("Finalizada", reserva.getEstado().getNombreEstado());
	}
	
	
	
	
	//------cancelados
	
	@Test
	void estadoInicialACancelado() {
		assertEquals("Pendiente" , reserva.getEstado().getNombreEstado());
		reserva.cancelar();
		assertEquals("Cancelada", reserva.getEstado().getNombreEstado());
	}
	
	@Test
	void deConfirmadaACancelado(){
		reserva.confirmar();
		assertEquals("Confirmada",reserva.getEstado().getNombreEstado());
		reserva.cancelar();
		//confirmar decrementa o bloquea el cupo disponible
		assertEquals("Cancelada", reserva.getEstado().getNombreEstado());
	}
		
	@Test
	void deEnCheckinACancelado() {
		reserva.confirmar();
		reserva.checkin();
		assertEquals("Checkin",reserva.getEstado().getNombreEstado());
		reserva.cancelar();
		//la cancelacion del checkin implica liberar cupo y reembolso total
		assertEquals("Cancelada", reserva.getEstado().getNombreEstado());
	}
	
	@Test
	void deCursoACancelado() {
		reserva.confirmar();
		reserva.checkin();
		reserva.enCurso();
		assertEquals("En curso", reserva.getEstado().getNombreEstado());
		reserva.cancelar();
		//La cancelacion de el en curso solo se reembolsa el costo de servicios pero no tasas
		assertEquals("Cancelada", reserva.getEstado().getNombreEstado());
	}
	
	
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


