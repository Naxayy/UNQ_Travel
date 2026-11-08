package ar.edu.unq.travel.reportes;

public class ElementoTxt implements Element {
    public void accept(Visitor visitor) {
        visitor.visitTxt(this);
    }
}
