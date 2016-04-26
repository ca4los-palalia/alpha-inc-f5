package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

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
@Table
public class AlmacenEntrada implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -216839248357811374L;
	private Long idAlmacenEntrada;
	private Area area;
	private Almacen almacen;
	private Cotizacion cotizacion;
	private OrdenCompra ordenCompra;
	private Integer cantidad;
	private Calendar fechaEntrada;
	private boolean activarCantidad;
	private Organizacion organizacion;
	private Usuarios usuario;
	private Producto producto;
	private Proveedor proveedor;
	private List<Area> areas;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdAlmacenEntrada() {
		return idAlmacenEntrada;
	}
	public void setIdAlmacenEntrada(Long idAlmacenEntrada) {
		this.idAlmacenEntrada = idAlmacenEntrada;
	}
	
	@OneToOne
	@JoinColumn(name = "area")
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
	@OneToOne
	@JoinColumn(name = "almacen")
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	
	@OneToOne
	@JoinColumn(name = "cotizacion")
	public Cotizacion getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	@Column
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@Column
	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	@Transient
	public boolean isActivarCantidad() {
		return activarCantidad;
	}
	public void setActivarCantidad(boolean activarCantidad) {
		this.activarCantidad = activarCantidad;
	}
	
	@OneToOne
	@JoinColumn(name = "ordenCompra")
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
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
	
	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@OneToOne
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	@Transient
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
}
