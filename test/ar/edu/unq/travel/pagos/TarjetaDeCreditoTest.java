package ar.edu.unq.travel.pagos;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TarjetaDeCreditoTest {

    private TarjetaDeCreditoAPI api;
    private Date vencimiento;
    private TarjetaDeCredito tarjeta;

    @BeforeEach
    public void setUp() {
        api = mock(TarjetaDeCreditoAPI.class);
        vencimiento = new Date();
        tarjeta = new TarjetaDeCredito(api, "4509123456789010", "123", vencimiento, "Banco Galicia");
    }

    @Test
    public void testValidarDatosDelegaEnLaApi() {
        when(api.validarDatos("4509123456789010", "123", vencimiento)).thenReturn(true);

        assertTrue(tarjeta.validarDatos());
        verify(api).validarDatos("4509123456789010", "123", vencimiento);
    }

    @Test
    public void testValidarDatosDevuelveFalseSiLaApiInvalidaLaTarjeta() {
        when(api.validarDatos("4509123456789010", "123", vencimiento)).thenReturn(false);

        assertFalse(tarjeta.validarDatos());
    }

    @Test
    public void testReservarFondosSolicitaPreautorizacionAlBanco() {
        tarjeta.reservarFondos();

        verify(api).preautorizacionAlBanco("Banco Galicia");
    }

    @Test
    public void testEjecutarTransaccionSolicitaTransferenciaInmediata() {
        tarjeta.ejecutarTransaccion();

        verify(api).transferenciaInmediata();
    }

    @Test
    public void testCodigoTransaccionDevuelveElCodigoDeLaApi() {
        when(api.getCodigoTransaccion()).thenReturn("TC-4455");

        assertEquals("TC-4455", tarjeta.codigoTransaccion());
        verify(api).getCodigoTransaccion();
    }

    @Test
    public void testNotificarResultadoConsultaCodigoEnLaApi() {
        tarjeta.notificarResultado();

        verify(api).getCodigoTransaccion();
    }

    @Test
    public void testProcesarPagoEjecutaElFlujoCompletoEnOrden() {
        when(api.validarDatos("4509123456789010", "123", vencimiento)).thenReturn(true);

        tarjeta.procesarPago();

        InOrder orden = inOrder(api);
        orden.verify(api).validarDatos("4509123456789010", "123", vencimiento);
        orden.verify(api).preautorizacionAlBanco("Banco Galicia");
        orden.verify(api).transferenciaInmediata();
        orden.verify(api).getCodigoTransaccion();
    }
}