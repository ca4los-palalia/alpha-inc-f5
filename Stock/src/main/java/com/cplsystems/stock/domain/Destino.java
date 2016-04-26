package com.cplsystems.stock.domain;

import java.io.Serializable;
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
public class Destino implements Serializable {
	private static final long serialVersionUID = -825268613541991802L;
	private Long idDestino;
	private Lugar lugar;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public Long getIdDestino() {
		return this.idDestino;
	}

	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}

	@OneToOne
	@JoinColumn(name = "Lugar")
	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
}
