package ar.edu.unq.travel.pagos;

public interface BilleteraVirtualAPI {
    public boolean haySaldoSuficiente(double saldo);
    public void bloquearSaldoHastaConfirmar();
    public void ejecutarTransaccion(String vendedor);
    public void notificacionPush();
}
