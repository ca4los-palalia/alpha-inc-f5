package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table
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
	private Calendar fechaActualizacion;
	private Organizacion organizacion;
	private Usuarios usuario;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProveedorProdcuto() {
		return this.idProveedorProdcuto;
	}

	public void setIdProveedorProdcuto(Long idProveedorProdcuto) {
		this.idProveedorProdcuto = idProveedorProdcuto;
	}

	@Column(name = "cantidad", length = 250)
	public String getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "descuento", length = 250)
	public String getDescuento() {
		return this.descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	@Column(name = "precio", length = 250)
	public String getPrecio() {
		return this.precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Column(name = "precioFinal", length = 250)
	public String getPrecioFinal() {
		return this.precioFinal;
	}

	public void setPrecioFinal(String precioFinal) {
		this.precioFinal = precioFinal;
	}

	@OneToOne
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@Temporal(TemporalType.DATE)
	@Column
	public Calendar getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@Transient
	public boolean isSeleccionar() {
		return this.seleccionar;
	}

	public void setSeleccionar(boolean seleccionar) {
		this.seleccionar = seleccionar;
	}
}
