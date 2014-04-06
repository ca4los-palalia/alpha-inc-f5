/**
 * 
 */
package com.cplsystems.incidencias.domain;

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
@Table(name = "personaDomicilio")
public class PersonaDomicilio {

	private Persona persona;
	private Domicilio domicilio;
	private Long idPersonaDomicilio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Personadomicilio", nullable = false)
	public Long getIdPersonaDomicilio() {
		return idPersonaDomicilio;
	}

	public void setIdPersonaDomicilio(Long idPersonaDomicilio) {
		this.idPersonaDomicilio = idPersonaDomicilio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domicilio")
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

}
