package ar.edu.unq.travel.busqueda;

import ar.edu.unq.travel.servicios.Servicio;

public class CriterioCategoria implements CriterioBusqueda {
    private Categoria categoriaEsperada;
    
    public CriterioCategoria(Categoria categoriaEsperada) {
        this.categoriaEsperada = categoriaEsperada;
    }

    @Override
    public boolean satisface(Servicio item) {
        return item.getCategoria() == this.categoriaEsperada;
    }
}
