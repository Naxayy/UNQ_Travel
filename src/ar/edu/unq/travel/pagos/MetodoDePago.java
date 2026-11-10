package ar.edu.unq.travel.pagos;

public abstract class MetodoDePago {
    private String codigoTransaccion;

    public void procesarPago() {
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract void validarDatos();

    public abstract void reservarFondos();

    public abstract void ejecutarTransaccion();
    
    public void notificarResultado() {
        
    };
}
