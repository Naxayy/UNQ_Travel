package ar.edu.unq.travel.reportes;

public class ReporteDeCatalogo implements Visitor {
    public ElementoTxt visitTxt(ElementoTxt txt) {
        return txt;
    }

    public ElementoCsv visitCsv(ElementoCsv csv) {
        return csv;
    }

    public ElementoHtml visitHtml(ElementoHtml html) {
        return html;
    }
}
