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
@Table
public class Lugar {
	private Long idLugar;
	private String nombre;
	private Proyecto proyecto;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdLugar() {
		return this.idLugar;
	}

	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto")
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}
