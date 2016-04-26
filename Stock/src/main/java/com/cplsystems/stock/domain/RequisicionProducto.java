package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
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
public class RequisicionProducto implements Serializable {
	private static final long serialVersionUID = -9121053303871749035L;
	private Long idRequisionProducto;
	private Float cantidad;
	private Long entregados;
	private String descripcion;
	private Lugar lugar;
	private Proveedor proveedor;
	private Requisicion requisicion;
	private Producto producto;
	private Float importe;
	private CofiaPartidaGenerica cofiaPartidaGenerica;
	private Float totalProductoPorUnidad;
	private Cotizacion cotizacion;
	private Organizacion organizacion;
	private Usuarios usuario;

	public RequisicionProducto() {
		this.producto = new Producto();
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdRequisionProducto() {
		return this.idRequisionProducto;
	}

	public void setIdRequisionProducto(Long idRequisionProducto) {
		this.idRequisionProducto = idRequisionProducto;
	}

	@Column(name = "cantidad", length = 250)
	public Float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	@OneToOne
	@JoinColumn(name = "lugar")
	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
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
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Column
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public Float getImporte() {
		return this.importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	@Transient
	public Float getTotalProductoPorUnidad() {
		if ((this.cantidad != null) && (this.producto != null) && (this.producto.getPrecio() != null)) {
			DecimalFormat formato = new DecimalFormat("###.##");
			this.totalProductoPorUnidad = Float
					.valueOf(this.cantidad.floatValue() * this.producto.getPrecio().floatValue());

			String flotante = formato.format(this.totalProductoPorUnidad);
			String nuevoFlotante = "";
			for (int i = 0; i < flotante.length(); i++) {
				String caracter = flotante.substring(i, i + 1);
				if (caracter.equals(",")) {
					caracter = ".";
				}
				nuevoFlotante = nuevoFlotante + caracter;
			}
			this.totalProductoPorUnidad = Float.valueOf(Float.parseFloat(nuevoFlotante));
		}
		return this.totalProductoPorUnidad;
	}

	public void setTotalProductoPorUnidad(Float totalProductoPorUnidad) {
		this.totalProductoPorUnidad = totalProductoPorUnidad;
	}

	@OneToOne
	@JoinColumn(name = "cofiaPartidaGenerica")
	public CofiaPartidaGenerica getCofiaPartidaGenerica() {
		return this.cofiaPartidaGenerica;
	}

	public void setCofiaPartidaGenerica(CofiaPartidaGenerica cofiaPartidaGenerica) {
		this.cofiaPartidaGenerica = cofiaPartidaGenerica;
	}

	@Column
	public Long getEntregados() {
		return this.entregados;
	}

	public void setEntregados(Long entregados) {
		this.entregados = entregados;
	}

	@OneToOne
	@JoinColumn(name = "cotizacion")
	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
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
}
