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
@Table(name = "OrdenCompraProducto")
public class OrdenCompraProducto {

	private Long idOrdenCompraProdcuto;
	private Integer cantidad;
	private Float importe;
	private Float precioUnitario;
	private Producto producto;
	private OrdenCompra ordencompra;

	@Id
	@Column(name = "idOrdenCompraProducto", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrdenCompraProdcuto() {
		return idOrdenCompraProdcuto;
	}

	public void setIdOrdenCompraProdcuto(Long idOrdenCompraProdcuto) {
		this.idOrdenCompraProdcuto = idOrdenCompraProdcuto;
	}

	@Column(name = "cantidad", length = 250)
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "importe", length = 250)
	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	@Column(name = "precioUnitario", length = 250)
	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "ordencompra")
	public OrdenCompra getOrdencompra() {
		return ordencompra;
	}

	public void setOrdencompra(OrdenCompra ordencompra) {
		this.ordencompra = ordencompra;
	}

}
