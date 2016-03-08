package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CotizacionProducto")
public class CotizacionProducto {
	private Long idCotizacion;
	private Long idCotizacionProducto;
	private Float cantidad;
	private Float precioUnitario;
	private Float importe;
	private Cotizacion cotizacion;
	private Producto producto;

	@Id
	@Column(name = "idCotizacion", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCotizacion() {
		return this.idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	@Column(name = "idCotizacionProdcuto", length = 250)
	public Long getIdCotizacionProducto() {
		return this.idCotizacionProducto;
	}

	public void setIdCotizacionProducto(Long idCotizacionProducto) {
		this.idCotizacionProducto = idCotizacionProducto;
	}

	@Column(name = "cantidad", length = 250)
	public Float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "precioUnitario", length = 250)
	public Float getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Column(name = "importe", length = 250)
	public Float getImporte() {
		return this.importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion")
	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
