package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.PeriodoDeTiempo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;

public class ReporteOcupacionCupos implements Element {

    private List<DatoCupo> datos;

    public ReporteOcupacionCupos(Catalogo catalogo, List<Reserva> reservas, List<Categoria> categorias, PeriodoDeTiempo periodo) {
        this.datos = new ArrayList<>();
        this.agregarCategorias(catalogo, reservas, categorias, periodo);
    } 
    
    private void agregarCategorias(Catalogo catalogo, List<Reserva> reservas, List<Categoria> categorias, PeriodoDeTiempo periodo){
        for (Categoria categoria : categorias) {
            this.datos.add(this.crearCupo(categoria, catalogo, reservas, periodo));
        }
    }

    private DatoCupo crearCupo(Categoria categoria, Catalogo catalogo, List<Reserva> reservas, PeriodoDeTiempo periodo) {
        return new DatoCupo(categoria, catalogo, reservas, periodo);
    }

    public List<DatoCupo> getDatos() {
        return this.datos;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitOcupacionCupos(this);
    }
}