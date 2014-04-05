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
@Table(name = "TipoDocumentoCalidad")
public class TipoDocumentoCalidad {

	private Long idTipoDocumentoCalidad;
	private String nombre;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoDocumentoCalidad", nullable = false)
	public Long getIdTipoDocumentoCalidad() {
		return idTipoDocumentoCalidad;
	}

	public void setIdTipoDocumentoCalidad(Long idTipoDocumentoCalidad) {
		this.idTipoDocumentoCalidad = idTipoDocumentoCalidad;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
