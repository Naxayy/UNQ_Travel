package ar.edu.unq.travel.pagos;

public interface TransferenciaBancariaAPI {
    public void validarDatos(String cbuCvu, String alias);
    public void transferenciaInmediataOProgramada();
}
