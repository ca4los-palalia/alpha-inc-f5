package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Telefono")
public class Telefono {

	private Long idTelefono;
	private String extension;
	private String numero;
	private String tipo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTelefono", nullable = false)
	public Long getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}

	@Column(name = "extension", length = 250)
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Column(name = "numero", length = 250)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "tipo", length = 250)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
