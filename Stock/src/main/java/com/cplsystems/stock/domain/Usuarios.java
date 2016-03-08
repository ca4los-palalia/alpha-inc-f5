package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 7367612078810564830L;
	private Long idUsuario;
	private String benutzer;
	private String kennwort;
	private Date fechaCaducidad;
	private Persona persona;
	private Organizacion organizacion;
	private String retypeKennwort;
	private Boolean owner = Boolean.valueOf(false);
	private Boolean client = Boolean.valueOf(false);
	private List<Privilegios> privilegios;

	public Usuarios() {
		this.privilegios = new ArrayList();
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column
	public String getBenutzer() {
		return this.benutzer;
	}

	public void setBenutzer(String benutzer) {
		this.benutzer = benutzer;
	}

	@Column
	public String getKennwort() {
		return this.kennwort;
	}

	public void setKennwort(String kennwort) {
		this.kennwort = kennwort;
	}

	@OneToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	@Transient
	public String getRetypeKennwort() {
		return this.retypeKennwort;
	}

	public void setRetypeKennwort(String retypeKennwort) {
		this.retypeKennwort = retypeKennwort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
	public List<Privilegios> getPrivilegios() {
		return this.privilegios;
	}

	public void setPrivilegios(List<Privilegios> privilegios) {
		this.privilegios = privilegios;
	}

	@Column
	public Boolean getOwner() {
		return this.owner;
	}

	public void setOwner(Boolean owner) {
		this.owner = owner;
	}

	public Boolean getClient() {
		return this.client;
	}

	public void setClient(Boolean client) {
		this.client = client;
	}
}
