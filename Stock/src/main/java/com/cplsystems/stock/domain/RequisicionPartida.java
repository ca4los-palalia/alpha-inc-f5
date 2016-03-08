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
@Table(name = "RequisicionPartida")
public class RequisicionPartida {
	private Long idPartida;
	private Partida partida;
	private Requisicion requisicion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPartida", nullable = false)
	public Long getIdPartida() {
		return this.idPartida;
	}

	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partida")
	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}
}
