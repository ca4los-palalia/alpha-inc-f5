package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class DireccionEntrega {
	private Long idDireccionEntrega;
	private Direccion direccion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public Long getIdDireccionEntrega() {
		return this.idDireccionEntrega;
	}

	public void setIdDireccionEntrega(Long idDireccionEntrega) {
		this.idDireccionEntrega = idDireccionEntrega;
	}

	@OneToOne
	@JoinColumn(name = "direccion")
	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
