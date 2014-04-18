package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RequsicionProveedor")
public class Contacto implements Serializable {

	private Long idContacto;
	private Telefono telefono;
	private Email email;

	@Id
	@Column(name = "idContactor", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Long idContacto) {
		this.idContacto = idContacto;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "telefono")
	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}