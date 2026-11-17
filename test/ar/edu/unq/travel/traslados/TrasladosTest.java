package ar.edu.unq.travel.traslados;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;

class TrasladosTest {

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
	

}


