package com.cplsystems.stock.domain;

import java.io.Serializable;
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
public class FamiliasProducto implements Serializable {
	private static final long serialVersionUID = -7955290534021913746L;
	private Long idFamiliasProducto;
	private ProductoTipo productoTipo;
	private Producto producto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdFamiliasProducto() {
		return this.idFamiliasProducto;
	}

	public void setIdFamiliasProducto(Long idFamiliasProducto) {
		this.idFamiliasProducto = idFamiliasProducto;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productoTipo")
	public ProductoTipo getProductoTipo() {
		return this.productoTipo;
	}

	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}

	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
