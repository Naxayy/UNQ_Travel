package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class State {
	private Reserva reserva;
	
	@BeforeEach
	
	void setUp() {
		reserva = new Reserva();
	}

	@Test
	void estadoInicial() {
		assertEquals(reserva.getEstado(), Pendiente);
		//todos los pedidos de estados posterior a confirmado son invalidos
		//solo aca se pueden agregar o quitar servicios
	}
	
	@Test
	void deInicialAConfirmada{
		assertEquals(reserva.getEstado(), Pendiente);
		reserva.confirmar();
		//todos los pedidos de estados anteriores son invalidos
		//confirmar decrementa o bloquea el cupo disponible
		assertEquals(reserva.getEstado(), Confirmada);
	}
		
	@Test
	void deConfirmadaAEnCheckin() {
		assertEquals(reserva.getEstado(), Confirmada);
		reserva.checkIn();
		//todos los pedidos de estados anteriores son invalidos
		//todos los pedidos de estados posterior a checkin son invalidos
		//la cancelacion del checkin implica liberar cupo y reembolso total
		assertEquals(reserva.getEstado(), CheckIn);
	}
	
	@Test
	void deCheckinACurso() {
		assertEquals(reserva.getEstado, CheckIn);
		reserva.enCurso();
		//todos los pedidos de estados anteriores son invalidos
		//La cancelacion de el en curso solo se reembolsa el costo de servicios pero no tasas
		assertEquals(reserva.getEstado, Curso);
	}
	
	@Test
	void deCursoAFinalizada() {
		assertEquals(reserva.getEstado , Curso);
		reserva.finalizar();
		//no se puede cancelar ni pasar a otro estado
		assertEquals(reserva.getEstado, Fianlizada);
	}
	
	
	
	
	//------cancelados
	
	@Test
	void estadoInicialACancelado() {
		assertEquals(reserva.getEstado(), Pendiente);
		reserva.cancelar();
		assertEquals(reserva.getEstado(), Cancelada);
	}
	
	@Test
	void deConfirmadaACancelado(){
		reserva.confirmar();
		assertEquals(reserva.getEstado(), Confirmada);
		reserva.cancelar();
		//confirmar decrementa o bloquea el cupo disponible
		assertEquals(reserva.getEstado(), Cancelada);
	}
		
	@Test
	void deEnCheckinACancelado() {
		reserva.checkIn();
		reserva.cancelar();
		//la cancelacion del checkin implica liberar cupo y reembolso total
		assertEquals(reserva.getEstado(), Cancelada);
	}
	
	@Test
	void deCursoACancelado() {
		assertEquals(reserva.getEstado, CheckIn);
		reserva.enCurso();
		reserva.cancelar();
		//La cancelacion de el en curso solo se reembolsa el costo de servicios pero no tasas
		assertEquals(reserva.getEstado, Cancelada);
	}
	
	
	//---------estados invalidos-----
	//Para todo esto hay que crear una clase para las excepciones y
	//cambiar el illegalStateException por la apropiada
	
	@Test
	void invalidoDesdeInicial() {
		//todos los pedidos de estados posterior a confirmado son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkIn();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizada();
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
			reserva.finalizada();
		});
	}
		
	@Test
	void invalidoDesdeCheckin() {
		//todos los pedidos de estados anteriores son invalidos
		//todos los pedidos de estados posterior a checkin son invalidos
		reserva.checkin();
		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmada();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.finalizada();
		});
	}
	
	@Test
	void invalidoDesdeEnCurso() {
		//todos los pedidos de estados anteriores son invalidos
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkIn();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmada();
		});
	}
	
	@Test
	void invalidoDesdeFinalizado() {
		//no se puede cancelar ni pasar a otro estado
		assertThrows(IllegalStateException.class, ()-> {
			reserva.checkIn();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.enCurso();
		});
		assertThrows(IllegalStateException.class, ()-> {
			reserva.confirmada();
		});
	}
	
	//--------------

	
	
	

}


