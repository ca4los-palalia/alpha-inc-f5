package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "proveedorProducto")
public class ProveedorProducto {

	
	private Long idProveedorProdcuto;
	private String cantidad;
	private String descuento;
	private String precio;
	private String precioFinal;
    private Proveedor proveedor;
    private Producto producto;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProveedorProducto", length = 250)
	public Long getIdProveedorProdcuto() {
		return idProveedorProdcuto;
	}

	public void setIdProveedorProdcuto(Long idProveedorProdcuto) {
		this.idProveedorProdcuto = idProveedorProdcuto;
	}

	@Column(name = "cantidad", length = 250)
	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "descuento", length = 250)
	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	@Column(name = "precio", length = 250)
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Column(name = "precioFinal", length = 250)
	public String getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(String precioFinal) {
		this.precioFinal = precioFinal;
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
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

}
