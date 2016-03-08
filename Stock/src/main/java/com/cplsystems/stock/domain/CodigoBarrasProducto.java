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
import javax.persistence.Transient;

@Entity
@Table
public class CodigoBarrasProducto {
	private Long idCodigoBarrasProducto;
	private String codigo;
	private Producto producto;
	private boolean selected;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCodigoBarrasProducto() {
		return this.idCodigoBarrasProducto;
	}

	public void setIdCodigoBarrasProducto(Long idCodigoBarrasProducto) {
		this.idCodigoBarrasProducto = idCodigoBarrasProducto;
	}

	@Column
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Transient
	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
