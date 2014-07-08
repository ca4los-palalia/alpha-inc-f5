package com.cplsystems.stock.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio implements Serializable {

	private static final long serialVersionUID = 2327486550971803091L;
	private Long idMunicipio;
	private String nombre;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_estado", nullable = false)
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
