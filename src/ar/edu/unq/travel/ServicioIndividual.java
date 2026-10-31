package ar.edu.unq.travel;

public class ServicioIndividual extends Servicio{
	private double precioBase;
	
	//Cambiar atributos metodos ??
	
	//private Proveedor provedor; 
	//private Categoria categoria;
	
	public ServicioIndividual(String nombre, Double precioBase, String descripcion){
		super(nombre, descripcion);
		this.precioBase = precioBase;
	}
	
	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	@Override
	public double precioFinal() { 
		//decidimos tomar precio final igual que el precio base
		return this.precioBase;
	}

}
