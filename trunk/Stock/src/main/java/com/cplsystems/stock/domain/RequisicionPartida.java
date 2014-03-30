package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RequisicionPartida")
public class RequisicionPartida {

	private Long idPartida;
	private Long idRequisicion;
	private Long idRequisicionPartida;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPartida", nullable = false)
	public Long getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	@Column(name = "idRequisicion", nullable = false)
	public Long getIdRequisicion() {
		return idRequisicion;
	}

	public void setIdRequisicion(Long idRequisicion) {
		this.idRequisicion = idRequisicion;
	}

	@Column(name = "idRequisicionPartida", nullable = false)
	public Long getIdRequisicionPartida() {
		return idRequisicionPartida;
	}

	public void setIdRequisicionPartida(Long idRequisicionPartida) {
		this.idRequisicionPartida = idRequisicionPartida;
	}

}
