package ar.edu.unq.travel;

public class ServicioIndividual implements Servicio{
	private String nombre;
	private Double precioBase;
	private String descripcion;
	
	//Cambiar atributos metodos:
	
	//private Proveedor provedor; 
	//private String id;
	//private Categoria categoria;
	
	public ServicioIndividual(String nombre, Double precioBase, String descripcion){
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.descripcion = descripcion;
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
