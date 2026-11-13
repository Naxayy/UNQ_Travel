package ar.edu.unq.travel.pagos;

public class BilleteraVirtual extends MetodoDePago {
    private BilleteraVirtualAPI api;
    private double saldo;
    private String vendedor;

    public boolean validarDatos() {
        return api.haySaldoSuficiente(saldo);
    };

    public void reservarFondos() {
        api.bloquearSaldoHastaConfirmar();
    };

    public void ejecutarTransaccion() {
        api.ejecutarTransaccion(vendedor);
    };

    public String codigoTransaccion() {
        return null;
    }

    @Override
    public void notificarResultado() {
        api.notificacionPush();
    };
}
