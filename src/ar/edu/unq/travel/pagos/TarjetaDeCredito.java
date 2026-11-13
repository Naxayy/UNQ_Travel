package ar.edu.unq.travel.pagos;

import java.util.Date;

public class TarjetaDeCredito extends MetodoDePago {
    private TarjetaDeCreditoAPI api;
    private String nroTarjeta;
    private String cvv;
    private Date vto;
    private String bancoEmisor;
    
    public boolean validarDatos() {
        return api.validarDatos(nroTarjeta, cvv, vto);
    };

    public void reservarFondos() {
        api.preautorizacionAlBanco(bancoEmisor);
    };

    public void ejecutarTransaccion() {
        api.transferenciaInmediata();
    };

    public String codigoTransaccion() {
        return api.getCodigoTransaccion();
    };

    public void notificarResultado() {
        api.getCodigoTransaccion();
    }
}
