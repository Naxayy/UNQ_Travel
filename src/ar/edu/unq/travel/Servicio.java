package ar.edu.unq.travel;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private int codigo;
	
	public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	public abstract double precioFinal();

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void getCodigo(int codigo) {
		this.codigo = codigo;
	}
}

