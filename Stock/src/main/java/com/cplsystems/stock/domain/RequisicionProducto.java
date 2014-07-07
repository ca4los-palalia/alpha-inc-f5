/**
 * 
 */
package com.cplsystems.stock.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RequsicionProducto")
public class RequisicionProducto {
	private Long idRequisionProducto;
	private Float cantidad;
	private String descripcion;
	private Lugar lugar;
	private Proveedor proveedor;
	private Requisicion requisicion;
	private Producto producto;
	private Float importe;
	private String partida;
	private CofiaPartidaGenerica cofiaPartidaGenerica;
	private Float totalProductoPorUnidad;
	
	public RequisicionProducto() {
		producto = new Producto();
	}

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

	@OneToOne//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lugar")
	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@OneToOne//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@OneToOne//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@OneToOne//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	@Column
	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}
	
	@Transient
	public Float getTotalProductoPorUnidad() {
		if(cantidad != null && (producto != null && producto.getPrecio() != null)){
			totalProductoPorUnidad = cantidad * producto.getPrecio();
		}
		return totalProductoPorUnidad;
	}

	public void setTotalProductoPorUnidad(Float totalProductoPorUnidad) {
		this.totalProductoPorUnidad = totalProductoPorUnidad;
	}
	@OneToOne
	@JoinColumn(name = "cofiaPartidaGenerica")
	public CofiaPartidaGenerica getCofiaPartidaGenerica() {
		return cofiaPartidaGenerica;
	}

	public void setCofiaPartidaGenerica(CofiaPartidaGenerica cofiaPartidaGenerica) {
		this.cofiaPartidaGenerica = cofiaPartidaGenerica;
	}
}
