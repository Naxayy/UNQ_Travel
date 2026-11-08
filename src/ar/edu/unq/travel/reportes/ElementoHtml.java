package ar.edu.unq.travel.reportes;

public class ElementoHtml implements Element {
    
    @Override 
    public void accept(Visitor visitor) {
        visitor.visitHtml(this);
    }
}
