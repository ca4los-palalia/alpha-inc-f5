/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ExperienciaLaboral")
public class ClavePersonal {

	private Long idClavePersonal;
	private String clave;
	private Calendar fechaCaducidad;
	private Boolean activo;
	private Persona persona;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdClavePersonal", nullable = false)
	public Long getIdClavePersonal() {
		return idClavePersonal;
	}

	public void setIdClavePersonal(Long idClavePersonal) {
		this.idClavePersonal = idClavePersonal;
	}

	@Column(name = "clave", length = 250)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaCaducidad")
	public Calendar getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Calendar fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	@Column(name = "activo", length = 250)
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
