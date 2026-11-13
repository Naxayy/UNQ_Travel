package ar.edu.unq.travel.reportes;

import ar.edu.unq.travel.Catalogo;

public class ReporteDeCupos implements Visitor {
    
    @Override 
    public ElementoTxt visitTxt(ElementoTxt txt) {
        return txt;
    }

    @Override 
    public ElementoCsv visitCsv(ElementoCsv csv) {
        return csv;
    }

    @Override
    public ElementoHtml visitHtml(ElementoHtml html) {
        return html;
    }

    private void generarReporte(Catalogo catalogo) {
        
    }
    
}
