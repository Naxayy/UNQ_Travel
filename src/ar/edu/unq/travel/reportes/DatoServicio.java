package ar.edu.unq.travel.reportes;

public class DatoServicio {
    private String nombre;
    private int cantidad;
    private double precioPromedio;

    public DatoServicio(String nombre, int cantidad, double precioPromedio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioPromedio = precioPromedio;
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public int getCantidad() { 
        return cantidad; 
    }

    public double getPrecioPromedio() { 
        return precioPromedio; 
    }
}