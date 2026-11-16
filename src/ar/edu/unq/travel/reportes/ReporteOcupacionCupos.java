package ar.edu.unq.travel.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.Catalogo;
import ar.edu.unq.travel.Reserva;
import ar.edu.unq.travel.categoria.Categoria;

public class ReporteOcupacionCupos implements Element {

    private List<DatoCupo> datos;

    public ReporteOcupacionCupos(Catalogo catalogo, List<Reserva> reservas, List<Categoria> categorias, LocalDate desde, LocalDate hasta) {
        this.datos = new ArrayList<>();
        for (Categoria categoria : categorias) {
            this.datos.add(new DatoCupo(categoria, catalogo, reservas, desde, hasta));
        }
    }

    public List<DatoCupo> getDatos() {
        return this.datos;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitOcupacionCupos(this);
    }
}