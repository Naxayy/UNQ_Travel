package ar.edu.unq.travel.ciclo;

public class Cancelada extends Estado {
    public Cancelada() {}

    @Override
    public boolean ocupaCupo() {
        return false;
    }
}
