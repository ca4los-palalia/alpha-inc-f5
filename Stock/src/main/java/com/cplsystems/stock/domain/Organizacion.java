package com.cplsystems.stock.domain;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.image.AImage;

@Entity
@Table
public class Organizacion implements Serializable {
	private static final long serialVersionUID = 4373490847821834679L;
	private Long idOrganizacion;
	private String nombre;
	private String rfc;
	private byte[] logotipo;
	private AImage logotipoAImage;
	
	private Direccion direccion;
	private Contacto contacto;
	private DevelopmentTool developmentTool;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrganizacion() {
		return this.idOrganizacion;
	}

	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	@OneToOne
	@JoinColumn(name = "direccion")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@OneToOne
	@JoinColumn(name = "contacto")
	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	@Column
	@Lob
	public byte[] getLogotipo() {
		return logotipo;
	}

	

	public void setLogotipo(byte[] logotipo) {
		this.logotipo = logotipo;
	}
	

	@Transient
	public AImage getLogotipoAImage() {
		if (logotipo != null) {
			try {
				logotipoAImage = new AImage("logoByte", logotipo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logotipoAImage;
	}

	public void setLogotipoAImage(AImage logotipoAImage) {
		this.logotipoAImage = logotipoAImage;
	}

	@OneToOne
	@JoinColumn(name = "developmentTool")
	public DevelopmentTool getDevelopmentTool() {
		return developmentTool;
	}

	public void setDevelopmentTool(DevelopmentTool developmentTool) {
		this.developmentTool = developmentTool;
	}
	
	
	
	
}
