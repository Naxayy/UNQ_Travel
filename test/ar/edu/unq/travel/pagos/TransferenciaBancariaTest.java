package ar.edu.unq.travel.pagos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class TransferenciaBancariaTest {

    private TransferenciaBancariaAPI api;
    private TransferenciaBancaria transferencia;

    @BeforeEach
    public void setUp() {
        api = mock(TransferenciaBancariaAPI.class);
        transferencia = new TransferenciaBancaria(api, "0000003100012345678901", "unq.travel.pagos");
    }

    @Test
    public void testValidarDatosDelegaEnLaApi() {
        when(api.validarDatos("0000003100012345678901", "unq.travel.pagos")).thenReturn(true);

        assertTrue(transferencia.validarDatos());
        verify(api).validarDatos("0000003100012345678901", "unq.travel.pagos");
    }

    @Test
    public void testValidarDatosDevuelveFalseCuandoLaApiRechaza() {
        when(api.validarDatos("0000003100012345678901", "unq.travel.pagos")).thenReturn(false);

        assertFalse(transferencia.validarDatos());
    }

    @Test
    public void testReservarFondosNoInteractuaConLaApi() {
        transferencia.reservarFondos();

        verifyNoInteractions(api);
    }

    @Test
    public void testEjecutarTransaccionDelegaEnLaApi() {
        transferencia.ejecutarTransaccion();

        verify(api).transferenciaInmediataOProgramada();
    }

    @Test
    public void testCodigoTransaccionDevuelveElCodigoDeLaApi() {
        when(api.getCodigoTransaccion()).thenReturn("TRF-9876");

        assertEquals("TRF-9876", transferencia.codigoTransaccion());
        verify(api).getCodigoTransaccion();
    }

    @Test
    public void testNotificarResultadoConsultaCodigoEnLaApi() {
        transferencia.notificarResultado();

        verify(api).getCodigoTransaccion();
    }

    @Test
    public void testProcesarPagoEjecutaElFlujoCompletoEnOrden() {
        when(api.validarDatos("0000003100012345678901", "unq.travel.pagos")).thenReturn(true);

        transferencia.procesarPago();

        InOrder orden = inOrder(api);
        orden.verify(api).validarDatos("0000003100012345678901", "unq.travel.pagos");
        orden.verify(api).transferenciaInmediataOProgramada();
        orden.verify(api).getCodigoTransaccion();
    }
}