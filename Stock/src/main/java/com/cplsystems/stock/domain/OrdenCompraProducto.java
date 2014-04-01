/**
 * 
 */
package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Producto;

@Entity
@Table(name = "OrdenCompraProducto")
public class OrdenCompraProducto implements Serializable {

	private static final long serialVersionUID = 4771608172356710976L;
	
	private Long idOrdenCompraProdcuto;
	private Integer cantidad;
	private Float importe;
	private Float precioUnitario;
	private Producto producto;
	private OrdenCompra ordenCompra;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrdenCompraProdcuto() {
		return idOrdenCompraProdcuto;
	}
	public void setIdOrdenCompraProdcuto(Long idOrdenCompraProdcuto) {
		this.idOrdenCompraProdcuto = idOrdenCompraProdcuto;
	}
	@Column
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	@Column
	public Float getImporte() {
		return importe;
	}
	
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	@Column
	public Float getPrecioUnitario() {
		return precioUnitario;
	}
	@Column
	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "idProducto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrdenCompra")
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	
	

}