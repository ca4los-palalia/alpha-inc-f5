/**
 * 
 */
package com.cplsystems.incidencias.domain;

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
public class Organizacion implements Serializable {

	private static final long serialVersionUID = 8823491225920555883L;
	private Long idOrganizacion;
	private String nombre;
	private String descripcion;
	private Persona responsable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
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

}
