/**
 * 
 */
package com.cplsystems.incidencias.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DatosPersonales")
public class DatosPersonales {

	private Long idDatosPersonales;
	private String nombrePadre;
	private String nombreMadre;
	private String medico;
	private String tipoSangre;
	private String padecimientosCronicos;
	private String medicamentosActuales;
	private String telefonoUrgencia;
	private String contactoEmergencia;
	private Persona persona;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDatosPersonales", nullable = false)
	public Long getIdDatosPersonales() {
		return idDatosPersonales;
	}

	public void setIdDatosPersonales(Long idDatosPersonales) {
		this.idDatosPersonales = idDatosPersonales;
	}

	@Column(name = "nombrePadre", length = 250)
	public String getNombrePadre() {
		return nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	@Column(name = "nombreMadre", length = 250)
	public String getNombreMadre() {
		return nombreMadre;
	}

	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}

	@Column(name = "medico", length = 250)
	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	@Column(name = "tipoSangre", length = 250)
	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	@Column(name = "padecimientosCronicos", length = 250)
	public String getPadecimientosCronicos() {
		return padecimientosCronicos;
	}

	public void setPadecimientosCronicos(String padecimientosCronicos) {
		this.padecimientosCronicos = padecimientosCronicos;
	}

	@Column(name = "anioInicio", length = 250)
	public String getMedicamentosActuales() {
		return medicamentosActuales;
	}

	public void setMedicamentosActuales(String medicamentosActuales) {
		this.medicamentosActuales = medicamentosActuales;
	}

	@Column(name = "telefonoUrgencia", length = 250)
	public String getTelefonoUrgencia() {
		return telefonoUrgencia;
	}

	public void setTelefonoUrgencia(String telefonoUrgencia) {
		this.telefonoUrgencia = telefonoUrgencia;
	}

	@Column(name = "contactoEmergencia", length = 250)
	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	@OneToOne
	@JoinColumn(name = "status")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
