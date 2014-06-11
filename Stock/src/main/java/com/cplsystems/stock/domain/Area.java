package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;


@Entity
@Table
public class Area implements Serializable {
	
	private static final long serialVersionUID = -7164588155326780268L;
	
	private Long idArea;
	private String nombre;
	private String descripcion;
	private String toolTipIndice;
	private String toolTipNombre;
	private boolean nuevoRegistro;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdArea() {
		return idArea;
	}
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
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
	@Column
	public String getToolTipIndice() {
		return toolTipIndice;
	}
	public void setToolTipIndice(String toolTipIndice) {
		this.toolTipIndice = toolTipIndice;
	}
	@Column
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