package ar.edu.unq.travel;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private int codigo;
	
	public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	public abstract Double precioFinal();

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getCodigo() {
		return codigo;
	}
}

