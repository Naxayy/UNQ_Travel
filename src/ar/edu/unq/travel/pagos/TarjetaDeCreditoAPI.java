package ar.edu.unq.travel.pagos;

import java.util.Date;

public interface TarjetaDeCreditoAPI {
    public void validarDatos(String nroTarjeta, String cvv, Date vto);
    public void preautorizacionAlBanco(String bancoEmisor);
    public void transferenciaInmediata();
}
