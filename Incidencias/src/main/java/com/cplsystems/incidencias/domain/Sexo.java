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
@Table(name = "Sexo")
public class Sexo {
	
	private Long idSexo;
	private String nombre;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSexo", nullable = false)
	public Long getIdSexo() {
		return idSexo;
	}
	public void setIdSexo(Long idSexo) {
		this.idSexo = idSexo;
	}
	
	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
