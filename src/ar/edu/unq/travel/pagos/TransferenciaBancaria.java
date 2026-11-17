package ar.edu.unq.travel.pagos;

public class TransferenciaBancaria extends MetodoDePago {
    private TransferenciaBancariaAPI api;
    private String cbu;
    private String alias;

    public TransferenciaBancaria(TransferenciaBancariaAPI api, String cbu, String alias){
        this.api = api;
        this.cbu = cbu;
        this.alias = alias;
    }

    public boolean validarDatos() {
        return api.validarDatos(cbu, alias);
    };

    public void reservarFondos() {
        //no aplica
    };

    public void ejecutarTransaccion() {
        api.transferenciaInmediataOProgramada();
    };

    public String codigoTransaccion() {
        return api.getCodigoTransaccion();
    };

    public void notificarResultado() {
        api.getCodigoTransaccion();
    };
}
