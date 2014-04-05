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
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Actividad")
public class Actividad {
	
	private Long idActividad;
	private DatosOrganizacionales datosorganizacionales;
	private TipoActividad tipoactividad;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdActividad", nullable = false)
	
	public Long getIdActividad() {
		return idActividad;
	}
	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}
	
	@ManyToOne
	@JoinColumn(name = "datosorganizacionales")
	public DatosOrganizacionales getDatosorganizacionales() {
		return datosorganizacionales;
	}
	public void setDatosorganizacionales(DatosOrganizacionales datosorganizacionales) {
		this.datosorganizacionales = datosorganizacionales;
	}
	@OneToOne
	@JoinColumn(name = "tipoactividad")
	public TipoActividad getTipoactividad() {
		return tipoactividad;
	}
	public void setTipoactividad(TipoActividad tipoactividad) {
		this.tipoactividad = tipoactividad;
	}
	
	

}
