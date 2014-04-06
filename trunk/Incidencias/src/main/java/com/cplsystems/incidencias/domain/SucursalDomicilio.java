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
public class SucursalDomicilio {
	private Long idSucursalDomicilio;
	private Domicilio domicilio;
	private Sucursal sucursal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdSucursalDomicilio() {
		return idSucursalDomicilio;
	}

	public void setIdSucursalDomicilio(Long idSucursalDomicilio) {
		this.idSucursalDomicilio = idSucursalDomicilio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domicilio")
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
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
