package com.cplsystems.stock.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "Proyecto")
public class Proyecto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Long idProyecto;
	private String nombre;
	
	@Id
	@Column (name = "idProyecto")
	public Long getIdProyecto() {
		return idProyecto;
	}
	
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	@Column (name = "nombre", length =250)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
