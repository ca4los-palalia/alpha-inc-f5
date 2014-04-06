/**
 * 
 */
package com.cplsystems.incidencias.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DatosAcademicos")
public class DatosAcademicos {

	private Long idDatosAcademicos;
	private NivelAcademico nivelacademico;
	private String especialidad;
	private String institucion;
	private Integer anioInicio;
	private Integer anioFin;
	private Status status;
	private Persona persona;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDatosAcademicos", nullable = false)
	public Long getIdDatosAcademicos() {
		return idDatosAcademicos;
	}

	public void setIdDatosAcademicos(Long idDatosAcademicos) {
		this.idDatosAcademicos = idDatosAcademicos;
	}

	@OneToOne
	@JoinColumn(name = "nivelacademico")
	public NivelAcademico getNivelacademico() {
		return nivelacademico;
	}

	public void setNivelacademico(NivelAcademico nivelacademico) {
		this.nivelacademico = nivelacademico;
	}

	@Column(name = "especialidad", length = 250)
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Column(name = "institucion", length = 250)
	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	@Column(name = "anioInicio")
	public Integer getAnioInicio() {
		return anioInicio;
	}

	public void setAnioInicio(Integer anioInicio) {
		this.anioInicio = anioInicio;
	}

	@Column(name = "anioFin")
	public Integer getAnioFin() {
		return anioFin;
	}

	public void setAnioFin(Integer anioFin) {
		this.anioFin = anioFin;
	}

	@OneToOne
	@JoinColumn(name = "status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
