package ar.edu.unq.travel.reportes;

public class ServicioMasReservado implements Visitor {
    
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

    private void generarReporte() {
        
    }
}
