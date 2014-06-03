/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.util.ArrayList;

import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Requisicion;


/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public abstract class BasicStructure extends ServiceLayer {

	private static final long serialVersionUID = 3686010678115196973L;

	public void init() {
		producto = new Producto();
		areas = new ArrayList<Area>();
		posiciones = new ArrayList<Posicion>();
		requisicion = new Requisicion();
	}
	
	public void newRecord() {

	}

	public void deleteRecord() {

	}

	public void saveChanges() {

	}

	public void performSerch() {

	}

}
