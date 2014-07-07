package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CofiaPartidaGenerica {

	private Long idCofiaPartidaGenerica;
	private String nombre;
	private Calendar ultimaActualizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCofiaPartidaGenerica() {
		return idCofiaPartidaGenerica;
	}

	public void setIdCofiaPartidaGenerica(Long idCofiaPartidaGenerica) {
		this.idCofiaPartidaGenerica = idCofiaPartidaGenerica;
	}
	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public Calendar getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(Calendar ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
}
