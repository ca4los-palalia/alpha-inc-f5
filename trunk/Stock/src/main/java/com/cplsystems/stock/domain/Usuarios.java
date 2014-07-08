/**
 * 
 */
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

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */

@Entity
@Table
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idUsuario;
	private String benutzer;
	private String kennwort;
	private Persona persona;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column
	public String getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(String benutzer) {
		this.benutzer = benutzer;
	}

	@Column
	public String getKennwort() {
		return kennwort;
	}

	public void setKennwort(String kennwort) {
		this.kennwort = kennwort;
	}

	@OneToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
