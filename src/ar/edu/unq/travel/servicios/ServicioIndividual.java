package ar.edu.unq.travel.servicios;

public class ServicioIndividual extends Servicio{
	private double precioBase;
	private int codigo;
	private Categoria categoria;
	
	//Cambiar atributos metodos ??
	//private Proveedor provedor; 
	
	public ServicioIndividual(String nombre, double precioBase, String descripcion, int codigo, Categoria categoria, int cuposDisponibles) {
		super(nombre, descripcion, cuposDisponibles);
		this.codigo = codigo;
		this.precioBase = precioBase;
		this.categoria = categoria;
	}

	public boolean contieneCategoria(Categoria categoria) {
		return this.categoria == categoria;
	}
	
	@Override
	public double precioFinal() { 
		//decidimos tomar precio final igual que el precio base
		return this.precioBase;
	}
	
	@Override
	public double precioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

}
