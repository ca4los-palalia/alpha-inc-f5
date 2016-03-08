package com.cplsystems.stock.domain;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Requsicion")
public class Requisicion {
	private Long idRequisicion;
	private String emailAlmacenista;
	private String clave;
	private Calendar fecha;
	private Calendar fechaEntrega;
	private Calendar fechaSolicitudProveedor;
	private Proyecto proyecto;
	private String emailPersonaRevisoRequisicion;
	private String emailPersonaSolicitante;
	private Area area;
	private Posicion posicion;
	private Persona persona;
	private String adscripcion;
	private String justificacion;
	private CofiaProg cofiaProg;
	private CofiaPy cofiaPy;
	private CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento;
	private Long numeroInventario;
	private String folio;
	private EstatusRequisicion estatusRequisicion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String buscarRequisicion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRequisicion", nullable = false)
	public Long getIdRequisicion() {
		return this.idRequisicion;
	}

	public void setIdRequisicion(Long idRequisicion) {
		this.idRequisicion = idRequisicion;
	}

	@Column(name = "emailAlmacenista", length = 250)
	public String getEmailAlmacenista() {
		return this.emailAlmacenista;
	}

	public void setEmailAlmacenista(String emailAlmacenista) {
		this.emailAlmacenista = emailAlmacenista;
	}

	@Column(name = "clave", length = 250)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "fecha", length = 250)
	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Column(name = "fecha_entrega", length = 250)
	public Calendar getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Calendar fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Column(name = "fecha_solicitud_proveedor", length = 250)
	public Calendar getFechaSolicitudProveedor() {
		return this.fechaSolicitudProveedor;
	}

	public void setFechaSolicitudProveedor(Calendar fechaSolicitudProveedor) {
		this.fechaSolicitudProveedor = fechaSolicitudProveedor;
	}

	@Column(name = "email_Personal_Resio_Requisicion", length = 250)
	public String getEmailPersonaRevisoRequisicion() {
		return this.emailPersonaRevisoRequisicion;
	}

	public void setEmailPersonaRevisoRequisicion(String emailPersonaRevisoRequisicion) {
		this.emailPersonaRevisoRequisicion = emailPersonaRevisoRequisicion;
	}

	@Column(name = "email_Personal_Solicitante", length = 250)
	public String getEmailPersonaSolicitante() {
		return this.emailPersonaSolicitante;
	}

	public void setEmailPersonaSolicitante(String emailPersonaSolicitante) {
		this.emailPersonaSolicitante = emailPersonaSolicitante;
	}

	@OneToOne
	@JoinColumn(name = "proyecto")
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@OneToOne
	@JoinColumn(name = "area")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@OneToOne
	@JoinColumn(name = "posicion")
	public Posicion getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	@Column
	public String getAdscripcion() {
		return this.adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	@Column
	public String getJustificacion() {
		return this.justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	@Column
	public Long getNumeroInventario() {
		return this.numeroInventario;
	}

	public void setNumeroInventario(Long numeroInventario) {
		this.numeroInventario = numeroInventario;
	}

	@OneToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Column
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@OneToOne
	@JoinColumn(name = "cofiaProg")
	public CofiaProg getCofiaProg() {
		return this.cofiaProg;
	}

	public void setCofiaProg(CofiaProg cofiaProg) {
		this.cofiaProg = cofiaProg;
	}

	@OneToOne
	@JoinColumn(name = "cofiaPy")
	public CofiaPy getCofiaPy() {
		return this.cofiaPy;
	}

	public void setCofiaPy(CofiaPy cofiaPy) {
		this.cofiaPy = cofiaPy;
	}

	@OneToOne
	@JoinColumn(name = "cofiaFuenteFinanciamiento")
	public CofiaFuenteFinanciamiento getCofiaFuenteFinanciamiento() {
		return this.cofiaFuenteFinanciamiento;
	}

	public void setCofiaFuenteFinanciamiento(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		this.cofiaFuenteFinanciamiento = cofiaFuenteFinanciamiento;
	}

	@OneToOne
	@JoinColumn(name = "estatusRequisicion")
	public EstatusRequisicion getEstatusRequisicion() {
		return this.estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}

	@Transient
	public String getBuscarRequisicion() {
		return this.buscarRequisicion;
	}

	public void setBuscarRequisicion(String buscarRequisicion) {
		this.buscarRequisicion = buscarRequisicion;
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
}
