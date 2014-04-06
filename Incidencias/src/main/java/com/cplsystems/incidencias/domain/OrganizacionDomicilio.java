/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;

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
public class OrganizacionDomicilio implements Serializable {

	private static final long serialVersionUID = 6379726458988016568L;
	private Long idOrganizacionDomicilio;
	private Organizacion organizacion;
	private Domicilio domicilio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrganizacionDomicilio() {
		return idOrganizacionDomicilio;
	}

	public void setIdOrganizacionDomicilio(Long idOrganizacionDomicilio) {
		this.idOrganizacionDomicilio = idOrganizacionDomicilio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
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
