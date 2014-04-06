/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Entity
@Table
public class Sucursal implements Serializable {

	private static final long serialVersionUID = 1446187208035766181L;
	private Long idSucursal;
	private String nombre;
	private String descripcion;
	private Persona responsable;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToOne
	@JoinColumn(name = "idPersona")
	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}
