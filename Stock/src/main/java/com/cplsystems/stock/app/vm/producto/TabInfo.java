package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.domain.ProductoTipo;

public class TabInfo {
	private String path;
	private String icono;
	private ProductoTipo productoTipo;
	private Integer index;

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public ProductoTipo getProductoTipo() {
		return this.productoTipo;
	}

	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}

	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
