package ar.edu.unq.travel.reportes;

public class ElementoCsv implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCsv(this);
    }
}
