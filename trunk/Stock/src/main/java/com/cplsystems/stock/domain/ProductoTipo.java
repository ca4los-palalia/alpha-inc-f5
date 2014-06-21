package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class ProductoTipo {

	private Long idProductoTipo;
	private String abreviatura;
	private String nombre;
	private String descripcion;
	private String icono;
	private String toolTipIndice;
	private String toolTipNombre;
	private boolean nuevoRegistro;
	private boolean seleccionar;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoTipo() {
		return idProductoTipo;
	}
	public void setIdProductoTipo(Long idProductoTipo) {
		this.idProductoTipo = idProductoTipo;
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
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	@Column
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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
	@Transient
	public boolean isSeleccionar() {
		return seleccionar;
	}
	public void setSeleccionar(boolean seleccionar) {
		this.seleccionar = seleccionar;
	}
	
}
