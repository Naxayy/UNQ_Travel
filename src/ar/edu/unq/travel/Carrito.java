package ar.edu.unq.travel;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;

public class Carrito {
    private List<Servicio> lista;

    public Carrito() {
        this.lista = new ArrayList<>();
    }

    public void addServicio(Servicio servicio){
        this.lista.add(servicio);
    }

    public void removeServicio(Servicio servicio){
        this.lista.remove(servicio);
    }

    public void confirmar(){
        this.lista.forEach(producto -> producto.confirmar());
    }

    public void cancelar(){
        this.lista.forEach(producto -> producto.cancelar());
        this.lista.clear();
    }

    public double tasasAdministrativas(){
        return 0;  //anotar que asumimos unas tasas
    }

    public double costoDeServicio(){
        return this.lista.stream()
            .mapToDouble(producto -> producto.precioFinal())
            .sum();
    }

    public List<Servicio> getServicios(){
        return this.lista;
    }

    public boolean contiene(Servicio servicio) {
       return getLista().contains(servicio);
    }

    private List<Servicio> getLista(){
        return lista;
    }
}
