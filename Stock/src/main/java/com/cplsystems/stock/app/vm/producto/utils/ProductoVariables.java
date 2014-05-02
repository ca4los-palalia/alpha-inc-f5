/**
 * 
 */
package com.cplsystems.stock.app.vm.producto.utils;

import java.util.List;

import org.zkoss.bind.Validator;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Producto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public abstract class ProductoVariables extends BasicStructure {

	private static final long serialVersionUID = -6626407102060983517L;

	protected String claveProducto;
	protected String nombreProducto;
	protected List<Producto> productoDB;
	protected Producto productoSeleccionado;

	protected Validator productoValidator = new ProductoValidator();

	public String getClaveProducto() {
		return claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public List<Producto> getProductoDB() {
		return productoDB;
	}

	public void setProductoDB(List<Producto> productoDB) {
		this.productoDB = productoDB;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Validator getProductoValidator() {
		return productoValidator;
	}

}
