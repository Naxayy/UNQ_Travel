package ar.edu.unq.travel;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;

public class Carrito {
    private List<Servicio> lista = new ArrayList();

    public void addServicio(Servicio servicio){
        this.lista.add(servicio);
    }

    public void confirmar(){
        this.lista.forEach(producto -> producto.confirmar());
    }

    public void cancelar(){
        this.lista.forEach(producto -> producto.cancelar());
    }

    public double tasasAdministrativas(){
        return 0;  //anotar que asumimos unas tasas
    }

    public double costoDeServicio(){
        return this.lista.stream()
            .mapToDouble(producto -> producto.precioFinal())
            .sum();
    }
}
