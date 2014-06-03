package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
public class Banco implements Serializable {
	
	private static final long serialVersionUID = -7164588155326780268L;
	
	private Long idBanco;
	private String nombre;
	private String url;
	
	private String toolTipIndice;
	private String toolTipNombre;
	private boolean nuevoRegistro;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Transient
	public String getToolTipIndice() {
		return toolTipIndice;
	}
	public void setToolTipIndice(String toolTipIndice) {
		this.toolTipIndice = toolTipIndice;
	}
	@Transient
	public String getToolTipNombre() {
		return toolTipNombre;
	}
	public void setToolTipNombre(String toolTipNombre) {
		this.toolTipNombre = toolTipNombre;
	}
	@Transient
	public boolean isNuevoRegistro() {
		return nuevoRegistro;
	}
	public void setNuevoRegistro(boolean nuevoRegistro) {
		this.nuevoRegistro = nuevoRegistro;
	}
	
		
}
