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
@Table(name = "direccion")
public class Direccion implements Serializable {

	private static final long serialVersionUID = -46282038792540869L;
	
	private Long idDireccion;
	private String calle;
	private String cuidad;
	private String colonia;
	private String cp;
	private String numExt;
	private String numInt;
	private Estado estado;
	private Municipio municipio;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}
	

	@Column(name = "calle", length = 250)
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column(name = "cuidad", length = 30)
	public String getCuidad() {
		return cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	@Column(name = "colonia", length = 250)
	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@Column(name = "cp", length = 250)
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "numExt", length = 250)
	public String getNumExt() {
		return numExt;
	}

	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}

	@Column(name = "numInt", length = 250)
	public String getNumInt() {
		return numInt;
	}

	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio")
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	

}
