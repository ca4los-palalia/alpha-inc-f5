package com.cplsystems.stock.domain;

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

import com.cplsystems.stock.app.utils.StockUtils;

@Entity
@Table
public class Unidad implements Serializable {

/**
	 * 
	 */
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
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@Column
	public Calendar getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	@Transient
	public Date getFechaActualizacionDate() {
		if(fechaActualizacion != null){
			fechaActualizacionDate = new StockUtils().convertirCalendarToDate(fechaActualizacion);
		}
		return fechaActualizacionDate;
	}
	public void setFechaActualizacionDate(Date fechaActualizacionDate) {
		/*if(fechaActualizacionDate != null){
			fechaActualizacion = new StockUtils().convertirDateToCalendar(fechaActualizacionDate);
		}*/
		this.fechaActualizacionDate = fechaActualizacionDate;
	}
	
	
}
