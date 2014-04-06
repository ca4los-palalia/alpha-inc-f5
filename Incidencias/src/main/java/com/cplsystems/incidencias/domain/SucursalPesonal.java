/**
 * 
 */
package com.cplsystems.incidencias.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Entity
@Table
public class SucursalPesonal {

	private Long idSucursalPersonal;
	private Persona persona;
	private Sucursal sucursal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdSucursalPersonal() {
		return idSucursalPersonal;
	}

	public void setIdSucursalPersonal(Long idSucursalPersonal) {
		this.idSucursalPersonal = idSucursalPersonal;
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
	@JoinColumn(name = "sucursal")
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
