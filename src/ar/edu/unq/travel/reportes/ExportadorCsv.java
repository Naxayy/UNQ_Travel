package ar.edu.unq.travel.reportes;

public class ExportadorCsv implements Visitor {

    @Override
    public String visitServiciosMasReservados(ReporteServiciosMasReservados reporte) {
        String resultado = "Item,Vendidos,PrecioPromedio\n";
        for (DatoServicio dato : reporte.getDatos()) {
            resultado += dato.getNombre() + "," + dato.getCantidad() + "," + dato.getPrecioPromedio() + "\n";
        }
        return resultado;
    }

    @Override
    public String visitOcupacionCupos(ReporteOcupacionCupos reporte) {
        String resultado = "Categoria,Totales,Reservados,Libres\n";
        for (DatoCupo dato : reporte.getDatos()) {
            resultado += dato.getCategoria() + "," + dato.getTotales() + "," + dato.getReservados() + "," + dato.getLibres() + "\n";
        }
        return resultado;
    }

    @Override
    public String visitExposicionProveedor(ReporteExposicionProveedor reporte) {
        String resultado = "Operador,Porcentaje\n";
        for (DatoProveedor dato : reporte.getDatos()) {
            resultado += dato.getOperador() + "," + dato.getPorcentaje() + "\n";
        }
        return resultado;
    }
}