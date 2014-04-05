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
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IdiomasPersona")
public class IdiomasPersona {

	private Idiomas idioma;
	private Persona persona;
	private Long idIdiomaPersona;

	@ManyToOne
	@JoinColumn(name = "idioma")
	public Idiomas getIdioma() {

		return idioma;
	}

	public void setIdioma(Idiomas idioma) {
		this.idioma = idioma;
	}

	@ManyToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idIdiomaPersona", nullable = false)
	public Long getIdIdiomaPersona() {
		return idIdiomaPersona;
	}

	public void setIdIdiomaPersona(Long idIdiomaPersona) {
		this.idIdiomaPersona = idIdiomaPersona;
	}

}
