package ar.edu.unq.travel.pagos;

import java.util.Date;

public class TarjetaDeCredito extends MetodoDePago {
    private TarjetaDeCreditoAPI api;
    private String nroTarjeta;
    private String cvv;
    private Date vto;
    private String bancoEmisor;

    public TarjetaDeCredito(TarjetaDeCreditoAPI api, String nroTarjeta, String cvv, Date vto, String bancoEmisor){
        this.api = api;
        this.nroTarjeta = nroTarjeta;
        this.cvv = cvv;
        this.vto = vto;
        this.bancoEmisor = bancoEmisor;
    }
    
    public void validarDatos() {
        api.validarDatos(nroTarjeta, cvv, vto);
    };

    public void reservarFondos() {
        api.preautorizacionAlBanco(bancoEmisor);
    };

    public void ejecutarTransaccion() {
        api.transferenciaInmediata();
    };

    public void notificarResultado() {
        
    };
}
