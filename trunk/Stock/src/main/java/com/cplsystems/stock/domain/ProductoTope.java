package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.engine.Cascade;

@Entity
@Table(name = "producto_tope")
public class ProductoTope {

	
	private Long idProductoTopo;
	private Producto producto;
	private Lugar lugar;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto_topo", nullable = false)
	public Long getIdProductoTopo() {
		return idProductoTopo;
	}

	public void setIdProductoTopo(Long idProductoTopo) {
		this.idProductoTopo = idProductoTopo;
	}
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public Lugar getLugar() {
		return lugar;
	}

	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "lugar")	
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
