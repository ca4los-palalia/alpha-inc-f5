package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {

	private Long idDireccion;
	private String calle;
	private String cuidad;
	private String colonia;
	private String cp;
	private String numExt;
	private String numInt;
	private Long idEstado;
	private Long idMunicipio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}
	
	
	@Column(name = "idEstado", nullable = false)
	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	@Column(name = "idMunicipio", nullable = false)
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
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

}
