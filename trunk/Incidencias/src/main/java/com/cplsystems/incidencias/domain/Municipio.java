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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Municipio")
public class Municipio {

	private Estado estado;
	private Long idmunicipio;
	private String nombre;
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Idmunicipio", nullable = false)
	public Long getIdmunicipio() {
		return idmunicipio;
	}

	public void setIdmunicipio(Long idmunicipio) {
		this.idmunicipio = idmunicipio;
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
