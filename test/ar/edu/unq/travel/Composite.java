package ar.edu.unq.travel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Composite {
	private ItemSimple noche;
	private ItemSimple excursion;
	private ItemSimple trasladoAH;
	private ItemCompuesto paqueteTravel;
	private ItemCompuesto paqueteDePaquetes;
	//creacion de items:
	//itemSimple (string nombre , int precioBase , string descrpicion)
	//itemCompuesto (String nombre , string descripcion , int descuento)
	
	@BeforeEach
	void setUp() {
		noche = new ItemSimple("Noche de hotel central",15000, "descripcion");
		excursion = new ItemSimple("Excursion guiada museo " , 4000,"Descripcion");
		trasladoAH = new ItemSimple("Traslado Aeropuerto-Hotel", 2000 ,"Descripcion");
		paqueteTravel = new ItemCompuesto ("Pack escapada urbana","descripcion",10) ;
		paqueteDePaquetes = new ItemCompuesto ("dasd","descripcion",10);
	}

	@Test
	void valoresDeItemSimple() {
		assertEquals(15000 , noche.getPrecio());
		assertEquals(4000 , excursion.getPrecio());
		assertEquals(2000, trasladoAH.getPrecio());
	}
	
	@Test
	void valorDePaquete() {
		paqueteTravel.addItem(noche);
		paqueteTravel.addItem(excursion);
		paqueteTravel.addItem(trasladoAH);
		assertEquals(18900, paqueteTravel.getPrecio());
	}
	
	@Test
	void valorDePaqueteDePaquetes() {
		paqueteDePaquetes.addItem(noche);
		paqueteDePaquetes.addItem(paqueteTravel);
		assertEquals(30510, paqueteDePaquetes.getPrecio());
		
	}
	
	

}
