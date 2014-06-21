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
public class FamiliasProducto {

	private Long idFamiliasProducto;
	private Producto producto;
	private ProductoTipo productoTipo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdFamiliasProducto() {
		return idFamiliasProducto;
	}
	public void setIdFamiliasProducto(Long idFamiliasProducto) {
		this.idFamiliasProducto = idFamiliasProducto;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productoTipo")
	public ProductoTipo getProductoTipo() {
		return productoTipo;
	}
	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}
		
}
