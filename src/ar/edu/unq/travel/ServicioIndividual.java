package ar.edu.unq.travel;

public class ServicioIndividual extends Servicio{
	private Double precioBase;
	
	//Cambiar atributos metodos:
	
	//private Proveedor provedor; 
	//private Categoria categoria;
	
	public ServicioIndividual(String nombre, Double precioBase, String descripcion){
		super(nombre, descripcion);
		this.precioBase = precioBase;
	}
	
	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
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

	public Object getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

}
