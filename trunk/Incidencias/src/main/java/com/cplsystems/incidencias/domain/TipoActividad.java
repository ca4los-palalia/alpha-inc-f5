/**
 * 
 */
package com.cplsystems.incidencias.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoActividad")
public class TipoActividad {

	private Long idTipoActividad;
	private String nombre;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoActividad", nullable = false)
	public Long getIdTipoActividad() {
		return idTipoActividad;
	}

	public void setIdTipoActividad(Long idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 250)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
