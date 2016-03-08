package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ClaveArmonizada implements Serializable {
	private static final long serialVersionUID = -8758393432856527807L;
	private Long idClaveArmonizada;
	private String clasificacionId;
	private String clasificacionNombre;
	private Integer grupo;
	private Integer subGrupo;
	private Integer clase;
	private String subclase;
	private String tipoDeBien;
	private String clave;
	private String descripcion;
	private Calendar fechaActualizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdClaveArmonizada() {
		return this.idClaveArmonizada;
	}

	public void setIdClaveArmonizada(Long idClaveArmonizada) {
		this.idClaveArmonizada = idClaveArmonizada;
	}

	@Column
	public String getClasificacionId() {
		return this.clasificacionId;
	}

	public void setClasificacionId(String clasificacionId) {
		this.clasificacionId = clasificacionId;
	}

	@Column
	public String getClasificacionNombre() {
		return this.clasificacionNombre;
	}

	public void setClasificacionNombre(String clasificacionNombre) {
		this.clasificacionNombre = clasificacionNombre;
	}

	@Column
	public Integer getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	@Column
	public Integer getSubGrupo() {
		return this.subGrupo;
	}

	public void setSubGrupo(Integer subGrupo) {
		this.subGrupo = subGrupo;
	}

	@Column
	public Integer getClase() {
		return this.clase;
	}

	public void setClase(Integer clase) {
		this.clase = clase;
	}

	@Column
	public String getSubclase() {
		return this.subclase;
	}

	public void setSubclase(String subclase) {
		this.subclase = subclase;
	}

	@Column
	public String getTipoDeBien() {
		return this.tipoDeBien;
	}

	public void setTipoDeBien(String tipoDeBien) {
		this.tipoDeBien = tipoDeBien;
	}

	@Column
	public String getClave() {
		this.clave = (String.valueOf(this.grupo) + String.valueOf(this.subGrupo) + String.valueOf(this.clase));
		if ((this.subclase != null) && (this.tipoDeBien != null)) {
			this.clave = (this.clave + this.subclase + this.tipoDeBien);
		}
		this.clave = (this.clave + " " + this.descripcion);

		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public Calendar getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
}
