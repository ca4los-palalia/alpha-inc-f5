package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "proveedorProducto")
public class ProveedorProducto implements Serializable {

	private static final long serialVersionUID = -4209263282220593763L;
	private Long idProveedorProdcuto;
	private String cantidad;
	private String descuento;
	private String precio;
	private String precioFinal;
	private Proveedor proveedor;
	private Producto producto;
	private boolean seleccionar;

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

	@OneToOne
	// @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@OneToOne
	// @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Transient
	public boolean isSeleccionar() {
		return seleccionar;
	}

	public void setSeleccionar(boolean seleccionar) {
		this.seleccionar = seleccionar;
	}
	
	
}
