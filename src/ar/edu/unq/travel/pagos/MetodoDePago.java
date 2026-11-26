package ar.edu.unq.travel.pagos;

public abstract class MetodoDePago {

    public void procesarPago() {
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract boolean validarDatos();

    public abstract void reservarFondos();

    public abstract void ejecutarTransaccion();
    
    public abstract void notificarResultado();

    //public abstract String codigoTransaccion();
}
