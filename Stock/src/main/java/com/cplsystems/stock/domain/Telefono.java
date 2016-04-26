package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Telefono {
	private Long idTelefono;
	private String extension;
	private String numero;
	private String tipo;
	private String fax;
	private String celular;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdTelefono() {
		return this.idTelefono;
	}

	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}

	@Column
	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Column
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
