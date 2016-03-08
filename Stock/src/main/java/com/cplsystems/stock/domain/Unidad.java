package com.cplsystems.stock.domain;

import com.cplsystems.stock.app.utils.StockUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Unidad implements Serializable {
	private static final long serialVersionUID = 4408663347154767420L;
	private Long idUnidad;
	private String nombre;
	private String abreviatura;
	private Organizacion organizacion;
	private Usuarios usuario;
	private Calendar fechaActualizacion;
	private Date fechaActualizacionDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Column
	public Calendar getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@Transient
	public Date getFechaActualizacionDate() {
		if (this.fechaActualizacion != null) {
			this.fechaActualizacionDate = new StockUtils().convertirCalendarToDate(this.fechaActualizacion);
		}
		return this.fechaActualizacionDate;
	}

	public void setFechaActualizacionDate(Date fechaActualizacionDate) {
		this.fechaActualizacionDate = fechaActualizacionDate;
	}
}
