package ar.edu.unq.travel.reportes;

public class ExportadorTxt implements Visitor {

    @Override
    public String visitServiciosMasReservados(ReporteServiciosMasReservados reporte) {
        String resultado = "Reporte de Servicios Mas Reservados\n";
        for (DatoServicio dato : reporte.getDatos()) {
            resultado += dato.getNombre() + " - Vendidos: " + dato.getCantidad() + " - Precio promedio: " + dato.getPrecioPromedio() + "\n";
        }
        return resultado;
    }

    @Override
    public String visitOcupacionCupos(ReporteOcupacionCupos reporte) {
        String resultado = "Reporte de Ocupacion y Cupos\n";
        for (DatoCupo dato : reporte.getDatos()) {
            resultado += dato.getCategoria() + " - Totales: " + dato.getTotales() + " - Reservados: " + dato.getReservados() + " - Libres: " + dato.getLibres() + "\n";
        }
        return resultado;
    }

    @Override
    public String visitExposicionProveedor(ReporteExposicionProveedor reporte) {
        String resultado = "Reporte de Exposicion por Proveedor\n";
        for (DatoProveedor dato : reporte.getDatos()) {
            resultado += dato.getOperador() + " - Porcentaje: " + dato.getPorcentaje() + "%\n";
        }
        return resultado;
    }
}
