package ar.edu.unq.travel.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.travel.atributosDinamicos.AtributoDinamico;
import ar.edu.unq.travel.categoria.*;

public class ServicioIndividual extends Servicio{
	private double precioBase;
	private int codigo;
	private List<AtributoDinamico> atributosDinamicos = new ArrayList<AtributoDinamico>();
	private Categoria categoria;
	
	//Cambiar atributos metodos ??
	//private Proveedor provedor; 
	
	public ServicioIndividual(String nombre, String descripcion, int codigo, Categoria categoria, double precioBase) {
		super(nombre, descripcion);
		this.codigo = codigo;
		this.precioBase = precioBase;
		this.categoria = categoria;
	}

	public boolean contieneCategoria(Categoria categoria) {
		return this.categoria == categoria;//ver
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
