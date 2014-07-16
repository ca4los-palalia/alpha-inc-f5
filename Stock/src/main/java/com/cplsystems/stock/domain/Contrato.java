package com.cplsystems.stock.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.cplsystems.stock.app.utils.StockUtils;

@Entity
@Table(name = "contrato")
public class Contrato {

	private Long idContrato;
	private String nombre;
	private Long diasPago;
	private Calendar fecha;
	private Calendar fechaVigenciaFin;
	private Calendar fechaVigenciaInicio;
	private Date fechaVigenciaFinDate;
	private Date fechaVigenciaInicioDate;
	
	private String descripcion;
	
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	@Column(name = "diaspago")
	public Long getDiasPago() {
		return diasPago;
	}

	public void setDiasPago(Long diasPago) {
		this.diasPago = diasPago;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaVigenciaFin")
	public Calendar getFechaVigenciaFin() {
		return fechaVigenciaFin;
	}

	public void setFechaVigenciaFin(Calendar fechaVigenciaFin) {
		this.fechaVigenciaFin = fechaVigenciaFin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaVigenciaInicio")
	public Calendar getFechaVigenciaInicio() {
		return fechaVigenciaInicio;
	}

	public void setFechaVigenciaInicio(Calendar fechaVigenciaInicio) {
		this.fechaVigenciaInicio = fechaVigenciaInicio;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Transient
	public Date getFechaVigenciaFinDate() {
		
		if(fechaVigenciaFin != null)
			fechaVigenciaFinDate = new StockUtils().convertirCalendarToDate(fechaVigenciaFin);
		
		return fechaVigenciaFinDate;
	}

	public void setFechaVigenciaFinDate(Date fechaVigenciaFinDate) {
		if(fechaVigenciaFinDate != null)
			fechaVigenciaFin = new StockUtils().convertirDateToCalendar(fechaVigenciaFinDate);
		this.fechaVigenciaFinDate = fechaVigenciaFinDate;
	}
	@Transient
	public Date getFechaVigenciaInicioDate() {
		if(fechaVigenciaInicio != null)
			fechaVigenciaInicioDate = new StockUtils().convertirCalendarToDate(fechaVigenciaInicio);
		return fechaVigenciaInicioDate;
	}

	public void setFechaVigenciaInicioDate(Date fechaVigenciaInicioDate) {
		if(fechaVigenciaInicioDate != null)
			fechaVigenciaInicio = new StockUtils().convertirDateToCalendar(fechaVigenciaInicioDate);
		this.fechaVigenciaInicioDate = fechaVigenciaInicioDate;
	}
	
}
