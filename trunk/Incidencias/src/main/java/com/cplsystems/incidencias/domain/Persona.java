/**
 * 
 */
package com.cplsystems.incidencias.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Persona")
public class Persona {

	private Long idPersona;
	private EstadoCivil estadocivil;
	private Sexo sexo;
	private Boolean dadoDeBajo;
	private Boolean asignacionClavePersonal;
	private String imagen;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPersona", nullable = false)
	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	@OneToOne
	@JoinColumn(name = "estadocivil")
	public EstadoCivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(EstadoCivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	@OneToOne
	@JoinColumn(name = "persona")
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Column(name = "dadoDeBajo", length = 250)
	public Boolean getDadoDeBajo() {
		return dadoDeBajo;
	}

	public void setDadoDeBajo(Boolean dadoDeBajo) {
		this.dadoDeBajo = dadoDeBajo;
	}

	@Column(name = "asignacionClavePersonal", length = 250)
	public Boolean getAsignacionClavePersonal() {
		return asignacionClavePersonal;
	}

	public void setAsignacionClavePersonal(Boolean asignacionClavePersonal) {
		this.asignacionClavePersonal = asignacionClavePersonal;
	}

	@Column(name = "imagen", length = 250)
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
