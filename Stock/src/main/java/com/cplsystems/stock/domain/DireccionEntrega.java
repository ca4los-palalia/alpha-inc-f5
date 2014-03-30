package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "direccion_entrega")
public class DireccionEntrega {

	private Long idDireccionEntrega;
	private Long idDireccion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_direccion_entrega", nullable = false)
	public Long getIdDireccionEntrega() {
		return idDireccionEntrega;
	}

	public void setIdDireccionEntrega(Long idDireccionEntrega) {
		this.idDireccionEntrega = idDireccionEntrega;
	}

	@Column(name = "id_direccion", nullable = false)
	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

}
