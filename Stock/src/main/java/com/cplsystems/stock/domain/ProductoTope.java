package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	private Long idProducto;
	private Long idLugar;
	private Long idProductoTopo;
	private Producto producto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false)
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	@Column(name = "id_lugar", nullable = false)
	public Long getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}

	@Column(name = "id_producto_topo", nullable = false)
	public Long getIdProductoTopo() {
		return idProductoTopo;
	}

	public void setIdProductoTopo(Long idProductoTopo) {
		this.idProductoTopo = idProductoTopo;
	}
	@OneToOne
	@JoinColumn (name = "idProducto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
