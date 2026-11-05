package ar.edu.unq.travel;


public abstract class Servicio {
	private String nombre;
	private String descripcion;
	private int cupo; 
	
	public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	// podria agregar tasas admi a cada servicio y que las sume 
	
	public abstract double precioBase();
	
	public abstract double precioFinal();

	public String getNombre() {
		return nombre;
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

	public void confirmar(){
		this.decrementarCupo(1);
	}

	private void decrementarCupo(int cant){
		this.cupo = cupo - cant;
	}

	public void cancelar(){
		this.cupo = cupo +1;
	}
}

