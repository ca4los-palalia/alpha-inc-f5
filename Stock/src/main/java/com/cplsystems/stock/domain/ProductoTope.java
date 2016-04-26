package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ProductoTope {
	private Long idProductoTopo;
	private Producto producto;
	private Lugar lugar;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoTopo() {
		return this.idProductoTopo;
	}

	public void setIdProductoTopo(Long idProductoTopo) {
		this.idProductoTopo = idProductoTopo;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lugar")
	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
