package ar.edu.unq.travel.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.Catalogo;

public class ReporteExposicionProveedor implements Element {

    private List<DatoProveedor> datos;

    public ReporteExposicionProveedor(Catalogo catalogo) {
        this.datos = new ArrayList<>();
        catalogo.proveedores().forEach(proveedor -> this.datos.add(new DatoProveedor(proveedor, catalogo)));
    }

    public List<DatoProveedor> getDatos() {
        return this.datos;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitExposicionProveedor(this);
    }
}