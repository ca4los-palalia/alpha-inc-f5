package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "idContrato", nullable = false)
	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	@Column(name = "diaspago", length = 250)
	public Long getDiasPago() {
		return diasPago;
	}

	public void setDiasPago(Long diasPago) {
		this.diasPago = diasPago;
	}

	@Column(name = "fecha", length = 250)
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Column(name = "fechaVigenciaFin", length = 250)
	public Calendar getFechaVigenciaFin() {
		return fechaVigenciaFin;
	}

	public void setFechaVigenciaFin(Calendar fechaVigenciaFin) {
		this.fechaVigenciaFin = fechaVigenciaFin;
	}

	@Column(name = "fechaVigenciaInicio", length = 250)
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
