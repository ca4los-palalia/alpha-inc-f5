package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contrato")
public class Contrato {

	private Long idContrato;
	private Long diasPago;
	private Calendar fecha;
	private Calendar fechaVigenciaFin;
	private Calendar fechaVigenciaInicio;
	private String descripcion;

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

}
