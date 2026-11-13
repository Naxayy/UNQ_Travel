package ar.edu.unq.travel.pagos;

public class TransferenciaBancaria extends MetodoDePago {
    private TransferenciaBancariaAPI api;
    private String cbuCvu;
    private String alias;

    public String getCbuCvu() {
        return this.cbuCvu;
    }

    public boolean validarDatos() {
        return api.validarDatos(cbuCvu, alias);
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
        this.getCbuCvu();
    };
}
