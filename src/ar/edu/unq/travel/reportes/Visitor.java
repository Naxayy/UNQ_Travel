package ar.edu.unq.travel.reportes;

public interface Visitor {
    public String visitServiciosMasReservados(ReporteServiciosMasReservados reporte);
    public String visitOcupacionCupos(ReporteOcupacionCupos reporte);
    public String visitExposicionProveedor(ReporteExposicionProveedor reporte);
}