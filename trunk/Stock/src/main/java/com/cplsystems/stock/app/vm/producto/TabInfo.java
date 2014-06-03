package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.domain.ProductoTipo;

public class TabInfo {

	private String path;
	private String icono;
	private ProductoTipo productoTipo;
	private Integer index;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public ProductoTipo getProductoTipo() {
		return productoTipo;
	}

	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
