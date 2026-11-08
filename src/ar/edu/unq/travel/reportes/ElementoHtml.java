package ar.edu.unq.travel.reportes;

public class ElementoHtml implements Element {
    public void accept(Visitor visitor) {
        visitor.visitHtml(this);
    }
}
