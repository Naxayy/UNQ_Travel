package ar.edu.unq.travel;

public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private final int codigo;
	
	protected Servicio(String nombre, String descripcion,int codigo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
		this.codigo = codigo;
    }
	
	public abstract double precioBase();
	
	public abstract double precioFinal();

	public String getNombre() {
		return nombre;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

