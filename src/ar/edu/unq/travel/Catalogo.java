package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.travel.busqueda.CriterioBusqueda;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.servicios.Proveedor;
import ar.edu.unq.travel.servicios.Servicio;

public class Catalogo {
    private List<Servicio> items;
    private CriterioBusqueda condicion;

    public Catalogo() {
        this.items = new ArrayList<>();
    }

    public List<Servicio> busqueda(CriterioBusqueda condicion) {
        this.setCondicion(condicion);
        return items.stream().filter(item -> condicion.satisface(item)).toList();
    }

    public List<Servicio> getItems() {
        return this.items;
    }

    public void agregarItem(Servicio item) {
        this.items.add(item);
    }

    private void setCondicion(CriterioBusqueda condicion) {
        this.condicion = condicion;
    }

    public int cuposDisponibles(Categoria categoria, LocalDate desde, LocalDate hasta) {
        return this.serviciosIndividuales().stream()
                .filter(servicio -> servicio.contieneCategoria(categoria))
                .mapToInt(servicio -> servicio.cuposDisponiblesEnPeriodo(desde, hasta))
                .sum();
    }

    public Set<Proveedor> proveedores() {
        Set<Proveedor> resultado = new HashSet<>();
        this.items.forEach(servicio -> servicio.agregarProveedoresA(resultado));
        return resultado;
    }

    public double porcentajeDeProveedor(Proveedor proveedor) {
        Set<Servicio> individuales = this.serviciosIndividuales();
        if (individuales.isEmpty()) {
            return 0.0;
        }
        long delProveedor = individuales.stream()
                .filter(servicio -> servicio.esDeProveedor(proveedor))
                .count();
        return (delProveedor * 100.0) / individuales.size();
    }

    private Set<Servicio> serviciosIndividuales() {
        Set<Servicio> individuales = new HashSet<>();
        this.items.forEach(servicio -> servicio.recolectarServiciosIndividuales(individuales));
        return individuales;
    }
}
