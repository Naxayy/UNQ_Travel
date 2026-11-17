package ar.edu.unq.travel;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.travel.busqueda.CriterioBusqueda;
import ar.edu.unq.travel.categoria.Categoria;
import ar.edu.unq.travel.servicios.Proveedor;
import ar.edu.unq.travel.servicios.Servicio;

class CatalogoTest {
    
    Catalogo miCatalogo;
    Servicio alojamiento;
    Servicio vuelo;
    Servicio paquete;
    CriterioBusqueda condicion;
    Catalogo catalogo;
    Servicio servicio1;
    Servicio servicio2;

    @BeforeEach
    public void setUp() {
        this.miCatalogo = new Catalogo();
        this.alojamiento = mock(Servicio.class);
        this.vuelo = mock(Servicio.class);
        this.paquete = mock(Servicio.class);
        this.condicion = mock(CriterioBusqueda.class);
        this.catalogo = new Catalogo();
        this.servicio1 = mock(Servicio.class);
        this.servicio2 = mock(Servicio.class);
    }

    @Test
    void seAgreganItemsALaLista() {
        miCatalogo.agregarItem(vuelo);
        miCatalogo.agregarItem(alojamiento);
        
        assertTrue(miCatalogo.getItems().contains(vuelo));
        assertTrue(miCatalogo.getItems().contains(alojamiento));
    }

    @Test
    void busquedaFiltrado() {
        miCatalogo.agregarItem(vuelo);
        miCatalogo.agregarItem(alojamiento);

        when(condicion.satisface(vuelo)).thenReturn(true);
        when(condicion.satisface(alojamiento)).thenReturn(false);
        
        assertEquals(
            List.of(vuelo),
            miCatalogo.busqueda(condicion)
        );
    }

    @Test
    public void testCatalogoIniciaVacioYPermiteAgregarItems() {
        assertTrue(catalogo.getItems().isEmpty());

        catalogo.agregarItem(servicio1);
        catalogo.agregarItem(servicio2);

        assertEquals(List.of(servicio1, servicio2), catalogo.getItems());
    }

    @Test
    public void testBusquedaFiltraLosItemsQueSatisfacenLaCondicion() {
        CriterioBusqueda condicion = mock(CriterioBusqueda.class);
        when(condicion.satisface(servicio1)).thenReturn(true);
        when(condicion.satisface(servicio2)).thenReturn(false);

        catalogo.agregarItem(servicio1);
        catalogo.agregarItem(servicio2);

        List<Servicio> resultado = catalogo.busqueda(condicion);

        assertEquals(List.of(servicio1), resultado);
        verify(condicion).satisface(servicio1);
        verify(condicion).satisface(servicio2);
    }

    @Test
    public void testBusquedaDevuelveListaVaciaCuandoNingunItemSatisface() {
        CriterioBusqueda condicion = mock(CriterioBusqueda.class);
        when(condicion.satisface(servicio1)).thenReturn(false);

        catalogo.agregarItem(servicio1);

        assertTrue(catalogo.busqueda(condicion).isEmpty());
    }

    @Test
    public void testCuposDisponiblesSumaCuposDeServiciosIndividualesDeLaCategoria() {
        Categoria alojamiento = mock(Categoria.class);
        LocalDate desde = LocalDate.of(2026, 12, 1);
        LocalDate hasta = LocalDate.of(2026, 12, 10);

        Servicio individual1 = mock(Servicio.class);
        Servicio individual2 = mock(Servicio.class);
        Servicio individualOtraCategoria = mock(Servicio.class);

        when(individual1.contieneCategoria(alojamiento)).thenReturn(true);
        when(individual1.cuposDisponiblesEnPeriodo(desde, hasta)).thenReturn(4);

        when(individual2.contieneCategoria(alojamiento)).thenReturn(true);
        when(individual2.cuposDisponiblesEnPeriodo(desde, hasta)).thenReturn(6);

        when(individualOtraCategoria.contieneCategoria(alojamiento)).thenReturn(false);

        doAnswer(invocation -> {
            Set<Servicio> acumulador = invocation.<Set<Servicio>>getArgument(0);
            acumulador.add(individual1);
            return null;
        }).when(servicio1).recolectarServiciosIndividuales(anySet());

        doAnswer(invocation -> {
            Set<Servicio> acumulador = invocation.<Set<Servicio>>getArgument(0);
            acumulador.add(individual2);
            acumulador.add(individualOtraCategoria);
            return null;
        }).when(servicio2).recolectarServiciosIndividuales(anySet());

        catalogo.agregarItem(servicio1);
        catalogo.agregarItem(servicio2);

        int cupos = catalogo.cuposDisponibles(alojamiento, desde, hasta);

        assertEquals(10, cupos);
        verify(individualOtraCategoria, never()).cuposDisponiblesEnPeriodo(desde, hasta);
    }

    @Test
    public void testCuposDisponiblesDevuelveCeroCuandoElCatalogoEstaVacio() {
        Categoria vuelo = mock(Categoria.class);
        LocalDate desde = LocalDate.of(2026, 12, 1);
        LocalDate hasta = LocalDate.of(2026, 12, 5);

        assertEquals(0, catalogo.cuposDisponibles(vuelo, desde, hasta));
    }

    @Test
    public void testProveedoresRecolectaProveedoresUnicosDeTodosLosItems() {
        Proveedor proveedorA = mock(Proveedor.class);
        Proveedor proveedorB = mock(Proveedor.class);

        doAnswer(invocation -> {
            Set<Proveedor> acumulador = invocation.<Set<Proveedor>>getArgument(0);
            acumulador.add(proveedorA);
            return null;
        }).when(servicio1).agregarProveedoresA(anySet());

        doAnswer(invocation -> {
            Set<Proveedor> acumulador = invocation.<Set<Proveedor>>getArgument(0);
            acumulador.add(proveedorA);
            acumulador.add(proveedorB);
            return null;
        }).when(servicio2).agregarProveedoresA(anySet());

        catalogo.agregarItem(servicio1);
        catalogo.agregarItem(servicio2);

        Set<Proveedor> proveedores = catalogo.proveedores();

        assertEquals(Set.of(proveedorA, proveedorB), proveedores);
    }

    @Test
    public void testPorcentajeDeProveedorDevuelveCeroCuandoNoHayServiciosIndividuales() {
        Proveedor proveedor = mock(Proveedor.class);

        assertEquals(0.0, catalogo.porcentajeDeProveedor(proveedor), 0.001);
    }

    @Test
    public void testPorcentajeDeProveedorCalculaPorcentajeSobreServiciosIndividualesUnicos() {
        Proveedor proveedorBuscado = mock(Proveedor.class);

        Servicio ind1 = mock(Servicio.class);
        Servicio ind2 = mock(Servicio.class);
        Servicio ind3 = mock(Servicio.class);
        Servicio ind4 = mock(Servicio.class);

        when(ind1.esDeProveedor(proveedorBuscado)).thenReturn(true);
        when(ind2.esDeProveedor(proveedorBuscado)).thenReturn(false);
        when(ind3.esDeProveedor(proveedorBuscado)).thenReturn(false);
        when(ind4.esDeProveedor(proveedorBuscado)).thenReturn(false);

        doAnswer(invocation -> {
            Set<Servicio> acumulador = invocation.<Set<Servicio>>getArgument(0);
            acumulador.add(ind1);
            acumulador.add(ind2);
            return null;
        }).when(servicio1).recolectarServiciosIndividuales(anySet());

        // ind1 también aparece dentro de servicio2 (por ejemplo, un paquete), pero HashSet lo cuenta una sola vez
        doAnswer(invocation -> {
            Set<Servicio> acumulador = invocation.<Set<Servicio>>getArgument(0);
            acumulador.add(ind1);
            acumulador.add(ind3);
            acumulador.add(ind4);
            return null;
        }).when(servicio2).recolectarServiciosIndividuales(anySet());

        catalogo.agregarItem(servicio1);
        catalogo.agregarItem(servicio2);

        assertEquals(25.0, catalogo.porcentajeDeProveedor(proveedorBuscado), 0.001);
    }
}

