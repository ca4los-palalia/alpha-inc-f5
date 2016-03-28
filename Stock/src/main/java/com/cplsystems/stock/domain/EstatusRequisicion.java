package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EstatusRequisicion {
	private Long idEstatusRequisicion;
	private String nombre;
	private String clave;
	private String color;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdEstatusRequisicion() {
		return this.idEstatusRequisicion;
	}

	public void setIdEstatusRequisicion(Long idEstatusRequisicion) {
		this.idEstatusRequisicion = idEstatusRequisicion;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(length = 1500)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Column
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
