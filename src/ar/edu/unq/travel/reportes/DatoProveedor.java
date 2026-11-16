package ar.edu.unq.travel.reportes;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.servicios.Proveedor;

public class DatoProveedor {

    private Proveedor proveedor;
    private Catalogo catalogo;

    public DatoProveedor(Proveedor proveedor, Catalogo catalogo) {
        this.proveedor = proveedor;
        this.catalogo = catalogo;
    }

    public String getProveedor() {
        return this.proveedor.getNombre();
    }

    public double getPorcentaje() {
        return this.catalogo.porcentajeDeProveedor(this.proveedor);
    }

    public String getOperador() {
        return this.proveedor.getNombre();
    }
}