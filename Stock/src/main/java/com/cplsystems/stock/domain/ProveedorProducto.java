package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedorProducto")
public class ProveedorProducto {

	private Long idProveedor;
	private Long idProdcuto;
	private Long idProveedorProdcuto;
	private String cantidad;
	private String descuento;
	private String precio;
	private String precioFinal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor", nullable = false)
	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column
	public Long getIdProdcuto() {
		return idProdcuto;
	}

	public void setIdProdcuto(Long idProdcuto) {
		this.idProdcuto = idProdcuto;
	}

	@Column
	public Long getIdProveedorProdcuto() {
		return idProveedorProdcuto;
	}

	public void setIdProveedorProdcuto(Long idProveedorProdcuto) {
		this.idProveedorProdcuto = idProveedorProdcuto;
	}

	@Column
	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Column
	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	@Column
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Column
	public String getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(String precioFinal) {
		this.precioFinal = precioFinal;
	}

}
