package ar.edu.unq.travel.pagos;

public class BilleteraVirtual extends MetodoDePago {
    private BilleteraVirtualAPI api;
    private double saldo;
    private String vendedor;

    public BilleteraVirtual(BilleteraVirtualAPI api, double saldo, String vendedor){
        this.api = api;
        this.saldo = saldo;
        this.vendedor = vendedor;
    }

    public boolean validarDatos() {
        return api.haySaldoSuficiente(saldo);
    };

    @Override
    public void reservarFondos() {
        api.bloquearSaldoHastaConfirmar();
    };

    @Override
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
