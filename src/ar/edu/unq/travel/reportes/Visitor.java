package ar.edu.unq.travel.reportes;

public interface Visitor {
    String visitOcupacionCupos(ReporteOcupacionCupos reporte);
    String visitExposicionProveedor(ReporteExposicionProveedor reporte);
    String visitServiciosMasReservados(ReporteServiciosMasReservados reporte);
}