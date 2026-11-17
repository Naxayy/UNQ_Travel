package ar.edu.unq.travel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.edu.unq.travel.servicios.Servicio;

public class Carrito {
    private List<Servicio> lista;

    public Carrito() {
        this.lista = new ArrayList<>();
    }

    public void addServicio(Servicio servicio, LocalDate fechaInicio, LocalDate fechaFin, int cantidadPasajeros){
        if (!servicio.hayDisponibilidad(fechaInicio, fechaFin, cantidadPasajeros)) {
			throw new IllegalStateException("No hay cupos disponibles para este servicio en las fechas seleccionadas");
		}
        this.lista.add(servicio);
    }

    public void removeServicio(Servicio servicio){
        this.lista.remove(servicio);
    }

    public void confirmar(LocalDate fechaInicio, LocalDate fechaFin,int cantidadPersonas){
        this.lista.forEach(producto -> producto.confirmar(fechaInicio, fechaFin, cantidadPersonas));
    }

    public void cancelar(LocalDate fechaInicio, LocalDate fechaFin,int cantidadPersonas){
        this.lista.forEach(producto -> producto.cancelar(fechaInicio, fechaFin, cantidadPersonas));
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

    public List<Servicio> getListaDelCarrito(){
        return Collections.unmodifiableList(this.lista);
    }

    public boolean contieneElServicio(Servicio servicio){
        return this.getListaDelCarrito().contains(servicio);
    }

}
