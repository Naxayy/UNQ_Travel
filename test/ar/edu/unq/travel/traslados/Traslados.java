package ar.edu.unq.travel.traslados;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import ar.edu.unq.travel.plataformaTransporte.PlataformaTransporte;
import ar.edu.unq.travel.traslados.TrasladoEstandar;
import ar.edu.unq.travel.traslados.TrasladoPunto;
import ar.edu.unq.travel.traslados.TrasladoVip;

class Traslados {

	private TrasladoEstandar trasladoEstandar;
	private TrasladoVip trasladoVip;
	private TrasladoPunto trasladoConPuntoDeEncuentro;
	private PlataformaTransporte plataformaTraslado = mock(PlataformaTransporte.class);
	
	@BeforeEach
	public void setUp() {
		trasladoEstandar = new TrasladoEstandar(plataformaTraslado);
		trasladoVip = new TrasladoVip(10, 500);
		trasladoConPuntoDeEncuentro = new TrasladoPunto();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}


