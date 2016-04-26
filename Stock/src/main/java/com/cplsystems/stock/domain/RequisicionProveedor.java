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

@Entity
@Table
public class RequisicionProveedor implements Serializable {
	private static final long serialVersionUID = 6555204962404224362L;
	private Long idRequisicionProveedor;
	private Integer status;
	private Proveedor proveedor;
	private Requisicion requisicion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdRequisicionProveedor() {
		return this.idRequisicionProveedor;
	}

	public void setIdRequisicionProveedor(Long idRequisicionProveedor) {
		this.idRequisicionProveedor = idRequisicionProveedor;
	}

	@Column(name = "status", length = 250)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
