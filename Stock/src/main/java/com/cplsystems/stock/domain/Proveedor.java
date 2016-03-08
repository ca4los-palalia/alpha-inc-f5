package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "provedor")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 5955369590591544443L;
	private Long idProveedor;
	private String comentario;
	private String clave;
	private String nombre;
	private String password;
	private String razonSocial;
	private String rfc;
	private String status;
	private boolean proveedorActivo;
	private Long cuentaCargo;
	private Calendar fechaActualizacion;
	private String paginaWeb;
	private Contacto contacto;
	private Contrato contrato;
	private Direccion direccionDevolucion;
	private Direccion direccionFiscal;
	private Persona director;
	private Persona gerenteFinanzas;
	private Persona gerenteVentas;
	private Persona representanteLegal;
	private Persona representanteAteCliente;
	private Giro giro;
	private CuentaPago cuentaPago;
	private Organizacion organizacion;
	private Usuarios usuario;

	@Id
	@Column(name = "idProveedor", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column
	public Long getCuentaCargo() {
		return this.cuentaCargo;
	}

	public void setCuentaCargo(Long cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}

	@Column
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column
	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Column
	public boolean isProveedorActivo() {
		return this.proveedorActivo;
	}

	public void setProveedorActivo(boolean proveedorActivo) {
		this.proveedorActivo = proveedorActivo;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Calendar getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column
	public String getPaginaWeb() {
		return this.paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	@OneToOne
	@JoinColumn(name = "contacto")
	public Contacto getContacto() {
		return this.contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@OneToOne
	@JoinColumn(name = "contrato")
	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@OneToOne
	@JoinColumn(name = "direccionDevolucion")
	public Direccion getDireccionDevolucion() {
		return this.direccionDevolucion;
	}

	public void setDireccionDevolucion(Direccion direccionDevolucion) {
		this.direccionDevolucion = direccionDevolucion;
	}

	@OneToOne
	@JoinColumn(name = "direccionFiscal")
	public Direccion getDireccionFiscal() {
		return this.direccionFiscal;
	}

	public void setDireccionFiscal(Direccion direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	@OneToOne
	@JoinColumn(name = "director")
	public Persona getDirector() {
		return this.director;
	}

	public void setDirector(Persona director) {
		this.director = director;
	}

	@OneToOne
	@JoinColumn(name = "gerenteFinanzas")
	public Persona getGerenteFinanzas() {
		return this.gerenteFinanzas;
	}

	public void setGerenteFinanzas(Persona gerenteFinanzas) {
		this.gerenteFinanzas = gerenteFinanzas;
	}

	@OneToOne
	@JoinColumn(name = "gerenteVentas")
	public Persona getGerenteVentas() {
		return this.gerenteVentas;
	}

	public void setGerenteVentas(Persona gerenteVentas) {
		this.gerenteVentas = gerenteVentas;
	}

	@OneToOne
	@JoinColumn(name = "representanteLegal")
	public Persona getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	@OneToOne
	@JoinColumn(name = "representanteAteCliente")
	public Persona getRepresentanteAteCliente() {
		return this.representanteAteCliente;
	}

	public void setRepresentanteAteCliente(Persona representanteAteCliente) {
		this.representanteAteCliente = representanteAteCliente;
	}

	@OneToOne
	@JoinColumn(name = "giro")
	public Giro getGiro() {
		return this.giro;
	}

	public void setGiro(Giro giro) {
		this.giro = giro;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@Transient
	public CuentaPago getCuentaPago() {
		return this.cuentaPago;
	}

	public void setCuentaPago(CuentaPago cuentaPago) {
		this.cuentaPago = cuentaPago;
	}
}
