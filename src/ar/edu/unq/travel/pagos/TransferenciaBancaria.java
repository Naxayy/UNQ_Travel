package ar.edu.unq.travel.pagos;

public class TransferenciaBancaria extends MetodoDePago {
    private TransferenciaBancariaAPI api;
    private String cbu;
    private String alias;

    public void validarDatos() {
        api.validarDatos(cbu, alias);
    };

    public void reservarFondos() {
        //no aplica
    };

    public void ejecutarTransaccion() {
        api.transferenciaInmediataOProgramada();
    };

    public void notificarResultado() {

    };
}
