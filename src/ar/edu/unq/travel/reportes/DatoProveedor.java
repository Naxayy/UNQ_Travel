package ar.edu.unq.travel.reportes;

public class DatoProveedor {
    private String operador;
    private double porcentaje;

    public DatoProveedor(String operador, double porcentaje) {
        this.operador = operador;
        this.porcentaje = porcentaje;
    }

    public String getOperador() { 
        return operador; 
    }

    public double getPorcentaje() { 
        return porcentaje; 
    }
    
}