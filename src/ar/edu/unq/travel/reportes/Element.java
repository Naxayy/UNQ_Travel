package ar.edu.unq.travel.reportes;

public interface Element {
    public void accept(Visitor visitor);
}
