package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@Table(name = "provedor")
public class Proveedor {

	private Long idProveedor;
	private String comentario;
	private String clave;
	private String giro;
	private String nombre;
	private String password;
	private String razonSocial;
	private String rfc;
	private String status;
	private Long cuentaCargo;
	private Contacto contacto;
	private Contrato contrato;
	private Direccion direccionDevolucion;
	private Direccion direccionFiscal;
	private Persona director;
	private Persona gerenteFinanzas;
	private Persona gerenteVentas;
	private Persona representanteLegal;
	private Persona representanteAteCliente;

	@Id
	@Column(name = "idProveedor", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	

	@Column
	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	
	
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contacto")
	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contrato")
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direccionDevolucion")
	public Direccion getDireccionDevolucion() {
		return direccionDevolucion;
	}

	public void setDireccionDevolucion(Direccion direccionDevolucion) {
		this.direccionDevolucion = direccionDevolucion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direccionFiscal")
	public Direccion getDireccionFiscal() {
		return direccionFiscal;
	}

	public void setDireccionFiscal(Direccion direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "director")
	public Persona getDirector() {
		return director;
	}

	public void setDirector(Persona director) {
		this.director = director;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gerenteFinanzas")
	public Persona getGerenteFinanzas() {
		return gerenteFinanzas;
	}

	public void setGerenteFinanzas(Persona gerenteFinanzas) {
		this.gerenteFinanzas = gerenteFinanzas;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gerenteVentas")
	public Persona getGerenteVentas() {
		return gerenteVentas;
	}

	public void setGerenteVentas(Persona gerenteVentas) {
		this.gerenteVentas = gerenteVentas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "representanteLegal")
	public Persona getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "representanteAteCliente")
	public Persona getRepresentanteAteCliente() {
		return representanteAteCliente;
	}

	public void setRepresentanteAteCliente(Persona representanteAteCliente) {
		this.representanteAteCliente = representanteAteCliente;
	}
	
	@Column
	public Long getCuentaCargo() {
		return cuentaCargo;
	}

	public void setCuentaCargo(Long cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	
	@Column
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	@Column
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	
	
}
