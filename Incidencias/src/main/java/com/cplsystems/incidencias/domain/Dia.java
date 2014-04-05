/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dia")
public class Dia {

	private Long idDia;
	private String dia;
	private Boolean laborable;
	private Calendar fecha;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDia", nullable = false)
	public Long getIdDia() {
		return idDia;
	}

	public void setIdDia(Long idDia) {
		this.idDia = idDia;
	}

	@Column(name = "dia", length = 250)
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	@Column(name = "laborable", length = 250)
	public Boolean getLaborable() {
		return laborable;
	}

	public void setLaborable(Boolean laborable) {
		this.laborable = laborable;
	}

	@Column(name = "fecha", length = 250)
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

}
