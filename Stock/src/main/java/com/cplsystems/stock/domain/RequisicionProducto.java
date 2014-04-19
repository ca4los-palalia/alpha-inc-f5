/**
 * 
 */
package com.cplsystems.stock.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RequsicionProducto")
public class RequisicionProducto {
	private Long idRequisionProducto;
	private Float cantidad;
	private Long idLugar;
	private Lugar lugar;
	private Proveedor proveedor;
	private Requisicion requisicion;
	private Producto producto;

	

	@Id
	@Column(name = "idRequisicionProducto", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdRequisionProducto() {
		return idRequisionProducto;
	}

	public void setIdRequisionProducto(Long idRequisionProducto) {
		this.idRequisionProducto = idRequisionProducto;
	}

	@Column(name = "cantidad", length = 250)
	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "idLugar", length = 250)
	public Long getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "lugar")
	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}