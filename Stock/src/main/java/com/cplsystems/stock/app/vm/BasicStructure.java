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

	private static final long serialVersionUID = 717655190907875708L;

	public void init() {
		producto = new Producto();
	}

}
