package ar.edu.unq.travel.pagos;

public class BilleteraVirtual extends MetodoDePago {
    private BilleteraVirtualAPI api;
    private double saldo;
    private String vendedor;

    public void validarDatos() {
        api.haySaldoSuficiente(saldo);
    };

    public void reservarFondos() {
        api.bloquearSaldoHastaConfirmar();
    };

    public void ejecutarTransaccion() {
        api.ejecutarTransaccion(vendedor);
    };

    @Override
    public void notificarResultado() {
        api.notificacionPush();
    };
}
