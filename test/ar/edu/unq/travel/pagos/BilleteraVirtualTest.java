package ar.edu.unq.travel.pagos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class BilleteraVirtualTest {

    private BilleteraVirtualAPI api;
    private BilleteraVirtual billetera;

    @BeforeEach
    public void setUp() {
        api = mock(BilleteraVirtualAPI.class);
        billetera = new BilleteraVirtual(api, 25000.0, "UNQ Travel");
    }

    @Test
    public void testValidarDatosVerificaSaldoSuficienteEnLaApi() {
        when(api.haySaldoSuficiente(25000.0)).thenReturn(true);

        assertTrue(billetera.validarDatos());
        verify(api).haySaldoSuficiente(25000.0);
    }

    @Test
    public void testValidarDatosDevuelveFalseSiNoAlcanzaElSaldo() {
        when(api.haySaldoSuficiente(25000.0)).thenReturn(false);

        assertFalse(billetera.validarDatos());
    }

    @Test
    public void testReservarFondosBloqueaSaldoHastaConfirmar() {
        billetera.reservarFondos();

        verify(api).bloquearSaldoHastaConfirmar();
    }

    @Test
    public void testEjecutarTransaccionEnviaElVendedorALaApi() {
        billetera.ejecutarTransaccion();

        verify(api).ejecutarTransaccion("UNQ Travel");
    }

    @Test
    public void testCodigoTransaccionDevuelveNullSinInteractuarConLaApi() {
        assertNull(billetera.codigoTransaccion());
        verifyNoInteractions(api);
    }

    @Test
    public void testNotificarResultadoEnviaNotificacionPush() {
        billetera.notificarResultado();

        verify(api).notificacionPush();
    }

    @Test
    public void testProcesarPagoEjecutaElFlujoCompletoEnOrden() {
        when(api.haySaldoSuficiente(25000.0)).thenReturn(true);

        billetera.procesarPago();

        InOrder orden = inOrder(api);
        orden.verify(api).haySaldoSuficiente(25000.0);
        orden.verify(api).bloquearSaldoHastaConfirmar();
        orden.verify(api).ejecutarTransaccion("UNQ Travel");
        orden.verify(api).notificacionPush();
    }
}