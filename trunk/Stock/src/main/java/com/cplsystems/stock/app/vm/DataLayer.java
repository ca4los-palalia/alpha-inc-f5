/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.io.Serializable;

import com.cplsystems.stock.domain.Producto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class DataLayer implements Serializable {

	private static final long serialVersionUID = -828756372536148348L;

	protected Producto producto;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
