package ar.edu.unq.travel;

public class Individual implements Catalogo{
	private String nombre;
	private Double precioBase;
	private String descripcion;
	
	
	//private Proveedor provedor;
	private String id;
	//private Categoria categoria;
	
	public Individual(String nombre, Double precioBase, String descripcion, String id){
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.descripcion = descripcion;
		this.id = id;
	}

	@Override
	public String nombre() {
		return nombre;
	}

	@Override
	public String descripcion() {
		return descripcion;
	}

	@Override
	public Double precioBase() {
		return this.precioBase;
	}

	@Override
	public Double precioFinal() {
		return this.precioBase;
	}

}
