package ar.edu.unq.travel.reportes;

public class ExportadorHtml implements Visitor {

    @Override
    public String visitServiciosMasReservados(ReporteServiciosMasReservados reporte) {
        String resultado = "<table><tr><th>Item</th><th>Vendidos</th><th>Precio Promedio</th></tr>";
        for (DatoServicio dato : reporte.getDatos()) {
            resultado += "<tr><td>" + dato.getNombre() + "</td><td>" + dato.getCantidad() + "</td><td>" + dato.getPrecioPromedio() + "</td></tr>";
        }
        resultado += "</table>";
        return resultado;
    }

    @Override
    public String visitOcupacionCupos(ReporteOcupacionCupos reporte) {
        String resultado = "<table><tr><th>Categoria</th><th>Totales</th><th>Reservados</th><th>Libres</th></tr>";
        for (DatoCupo dato : reporte.getDatos()) {
            resultado += "<tr><td>" + dato.getCategoria() + "</td><td>" + dato.getTotales() + "</td><td>" + dato.getReservados() + "</td><td>" + dato.getLibres() + "</td></tr>";
        }
        resultado += "</table>";
        return resultado;
    }

    @Override
    public String visitExposicionProveedor(ReporteExposicionProveedor reporte) {
        String resultado = "<table><tr><th>Operador</th><th>Porcentaje</th></tr>";
        for (DatoProveedor dato : reporte.getDatos()) {
            resultado += "<tr><td>" + dato.getOperador() + "</td><td>" + dato.getPorcentaje() + "</td></tr>";
        }
        resultado += "</table>";
        return resultado;
    }
}