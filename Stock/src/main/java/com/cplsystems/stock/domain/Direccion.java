package com.cplsystems.stock.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Direccion implements Serializable {
	private static final long serialVersionUID = -46282038792540869L;
	private Long idDireccion;
	private String calle;
	private String colonia;
	private String cp;
	private String cuidad;
	private String numExt;
	private String numInt;
	private Estado estado;
	private Municipio municipio;
	private Pais pais;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	@Column
	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column
	public String getCuidad() {
		return this.cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	@Column
	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@Column
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column
	public String getNumExt() {
		return this.numExt;
	}

	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}

	@Column
	public String getNumInt() {
		return this.numInt;
	}

	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio")
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais")
	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
