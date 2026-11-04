package ar.edu.unq.travel;

import java.util.ArrayList;
import java.util.List;

public class ServicioIndividual extends Servicio{
	private double precioBase;
	private int codigo;
	private List<AtributoDinamico> atributosDinamicos = new ArrayList<AtributoDinamico>();

	//private Proveedor provedor; 
	//private Categoria categoria;
	
	public ServicioIndividual(String nombre, double precioBase, String descripcion, int codigo){
		super(nombre, descripcion);
		this.codigo = codigo;
		this.precioBase = precioBase;
	}
	
	public ServicioIndividual(String nombre, double precioBase, String descripcion, int codigo, AtributoDinamico atributo){
		super(nombre, descripcion);
		this.codigo = codigo;
		this.precioBase = precioBase;
		this.agregarAtributo(atributo);
	}

	public void agregarAtributo(AtributoDinamico atributo){
		atributosDinamicos.add(atributo);
	}

	public List<AtributoDinamico> getAtributosDinamicos() {
		return atributosDinamicos;
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
	
	public int getCodigo() {
		return codigo;
	}

}
