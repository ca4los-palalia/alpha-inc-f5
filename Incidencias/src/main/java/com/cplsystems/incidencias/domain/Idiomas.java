/**
 * 
 */
package com.cplsystems.incidencias.domain;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Idiomas")
public class Idiomas {
	
	private Long idIdiomas;
	private String idioma;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idIdiomas", nullable = false)
	public Long getIdIdiomas() {
		return idIdiomas;
	}
	public void setIdIdiomas(Long idIdiomas) {
		this.idIdiomas = idIdiomas;
	}
	
	@Column(name = "idioma", length = 250)
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	

}
