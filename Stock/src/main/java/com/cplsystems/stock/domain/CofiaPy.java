package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class CofiaPy {

	private Long idCofiaPy;
	private String nombre;
	private Calendar ultimaActualizacion;

	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCofiaPy() {
		return idCofiaPy;
	}

	public void setIdCofiaPy(Long idCofiaPy) {
		this.idCofiaPy = idCofiaPy;
	}
	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public Calendar getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(Calendar ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
}