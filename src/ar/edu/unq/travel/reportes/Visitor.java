package ar.edu.unq.travel.reportes;

public interface Visitor {
    public ElementoTxt visitTxt(ElementoTxt txt);
    public ElementoCsv visitCsv(ElementoCsv csv);
    public ElementoHtml visitHtml(ElementoHtml html);
}
