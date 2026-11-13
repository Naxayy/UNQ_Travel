package ar.edu.unq.travel.pagos;

public interface TransferenciaBancariaAPI {
    public boolean validarDatos(String cbuCvu, String alias);
    public void transferenciaInmediataOProgramada();
    public String getCodigoTransaccion();
}
