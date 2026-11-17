package ar.edu.unq.travel.pagos;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;

public class MetodoDePagoTest {

    @Test
    public void testProcesarPagoEjecutaLosPasosEnOrden() {
        MetodoDePago metodo = spy(new MetodoDePago() {
            @Override
            public boolean validarDatos() {
                return true;
            }

            @Override
            public void reservarFondos() {
            }

            @Override
            public void ejecutarTransaccion() {
            }

            @Override
            public String codigoTransaccion() {
                return "TX-001";
            }

            @Override
            public void notificarResultado() {
            }
        });

        metodo.procesarPago();

        InOrder orden = inOrder(metodo);
        orden.verify(metodo).validarDatos();
        orden.verify(metodo).reservarFondos();
        orden.verify(metodo).ejecutarTransaccion();
        orden.verify(metodo).notificarResultado();
    }
}