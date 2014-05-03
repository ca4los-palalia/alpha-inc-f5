package com.cplsystems.stock.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity 
@Table (name = "email")
public class Email implements Serializable{
	
	private Long idEmails;
	private String contacto;
	private String email; 
	private String tipo;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "idEmails" , nullable = false)
	public Long getIdEmails() {
		return idEmails;
	}
	public void setIdEmails(Long idEmails) {
		this.idEmails = idEmails;
	}
	
	@Column
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	
	@Column
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Column
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
