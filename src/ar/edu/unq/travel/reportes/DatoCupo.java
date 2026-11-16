package ar.edu.unq.travel.reportes;

public class DatoCupo {
    private String categoria;
    private int totales;
    private int reservados;
    private int libres;

    public DatoCupo(String categoria, int totales, int reservados, int libres) {
        this.categoria = categoria;
        this.totales = totales;
        this.reservados = reservados;
        this.libres = libres;
    }

    public String getCategoria() { 
        return categoria; 
    }

    public int getTotales() { 
        return totales; 
    }

    public int getReservados() { 
        return reservados; 
    }
    
    public int getLibres() { 
        return libres; 
    }
}