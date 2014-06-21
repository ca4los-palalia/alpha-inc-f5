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
public class Moneda{
	
	private static final long serialVersionUID = -7164588155326780268L;
	
	private Long idMoneda;
	private String nombre;
	private String simbolo;
	private String toolTipIndice;
	private String toolTipNombre;
	private boolean nuevoRegistro;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(Long idMoneda) {
		this.idMoneda = idMoneda;
	}
	@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
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
