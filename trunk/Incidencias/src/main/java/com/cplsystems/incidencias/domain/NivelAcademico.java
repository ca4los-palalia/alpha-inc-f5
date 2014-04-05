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
@Table(name = "NivelAcademico")
public class NivelAcademico {

	private Long idniveAcademico;
	private String nombre;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idniveAcademico", nullable = false)
	public Long getIdniveAcademico() {
		return idniveAcademico;
	}

	public void setIdniveAcademico(Long idniveAcademico) {
		this.idniveAcademico = idniveAcademico;
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
