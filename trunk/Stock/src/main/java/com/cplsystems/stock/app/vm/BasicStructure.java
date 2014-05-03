/**
 * 
 */
package com.cplsystems.stock.app.vm;

import com.cplsystems.stock.domain.Producto;


/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public abstract class BasicStructure extends ServiceLayer {

	private static final long serialVersionUID = 3686010678115196973L;

	public void init() {
		producto = new Producto();
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
