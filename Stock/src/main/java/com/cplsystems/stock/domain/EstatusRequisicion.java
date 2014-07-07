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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdEstatusRequisicion() {
		return idEstatusRequisicion;
	}
	public void setIdEstatusRequisicion(Long idEstatusRequisicion) {
		this.idEstatusRequisicion = idEstatusRequisicion;
	}
	@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
}
