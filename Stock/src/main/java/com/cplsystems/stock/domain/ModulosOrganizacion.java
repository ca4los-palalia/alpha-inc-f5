/**
 * 
 */
package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.Column;
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
public class ModulosOrganizacion implements Serializable {

	private static final long serialVersionUID = 5251584456310433341L;

	private Long idModulosOrganizacion;
	private Organizacion organizacion;
	private Modulos modulos;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdModulosOrganizacion() {
		return idModulosOrganizacion;
	}

	public void setIdModulosOrganizacion(Long idModulosOrganizacion) {
		this.idModulosOrganizacion = idModulosOrganizacion;
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
	@JoinColumn(name = "modulos")
	public Modulos getModulos() {
		return modulos;
	}

	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}

}
