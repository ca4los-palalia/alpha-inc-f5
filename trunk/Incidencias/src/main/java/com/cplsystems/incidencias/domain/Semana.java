/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Semana")
public class Semana {

	private Long idHorarioLaboral;
	private String nombre;
	private String descripcion;
	private Calendar horaEntrada;
	private Calendar horaSalida;
	private Integer numeroSemana;
	private Integer horaSalidaComida;
	private Integer horaEntradaComida;
	private Boolean horaComida;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idHorarioLaboral", nullable = false)
	public Long getIdHorarioLaboral() {
		return idHorarioLaboral;
	}

	public void setIdHorarioLaboral(Long idHorarioLaboral) {
		this.idHorarioLaboral = idHorarioLaboral;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 250)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaEntrada", length = 250)
	public Calendar getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Calendar horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaSalida", length = 250)
	public Calendar getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Column(name = "numeroSemana", length = 250)
	public Integer getNumeroSemana() {
		return numeroSemana;
	}

	public void setNumeroSemana(Integer numeroSemana) {
		this.numeroSemana = numeroSemana;
	}

	@Column(name = "horaSalidaComida", length = 250)
	public Integer getHoraSalidaComida() {
		return horaSalidaComida;
	}

	public void setHoraSalidaComida(Integer horaSalidaComida) {
		this.horaSalidaComida = horaSalidaComida;
	}

	@Column(name = "horaEntradaComida", length = 250)
	public Integer getHoraEntradaComida() {
		return horaEntradaComida;
	}

	public void setHoraEntradaComida(Integer horaEntradaComida) {
		this.horaEntradaComida = horaEntradaComida;
	}

	@Column(name = "horaComida", length = 250)
	public Boolean getHoraComida() {
		return horaComida;
	}

	public void setHoraComida(Boolean horaComida) {
		this.horaComida = horaComida;
	}

}
