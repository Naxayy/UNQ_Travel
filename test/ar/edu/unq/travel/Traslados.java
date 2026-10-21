package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Traslados {

	private TrasladoEstandar trasladoEstandar;
	private TrasladoVip trasladoVip;
	private TrasladoPuntoDeEncuentro trasladoConPuntoDeEncuentro;
	
	@BeforeEach
	void setUp() {
		trasladoEstandar = new TrasladoEstandar();
		trasladoVip = new TrasladoVip();
		trasladoConPuntoDeEncuentro = new TrasladoPuntoDeEncuentro();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}


