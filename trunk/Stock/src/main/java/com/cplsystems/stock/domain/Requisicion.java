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
//	private Long entregarEn;
	private Direccion entregarEn;
	private Calendar fecha;
	private Calendar fechaEntrega;
	private Calendar fechaSolicitudProveedor;
	private Proyecto proyecto;
	private String emailPersonaRevisoRequisicion;
	private String emailPersonaSolicitante;
	private String status;
	private Area area;
	private Posicion posicion; //Puesto
	private String prog;
	private String py;
	private Persona persona;//Solicitante
	private String adscripcion;
	private String justificacion;
	private String partidaGenerica;
	private String fuenteFinanciamiento;
	private Long numeroInventario;
	private String folio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRequisicion", nullable = false)
	public Long getIdRequisicion() {
		return idRequisicion;
	}

	public void setIdRequisicion(Long idRequisicion) {
		this.idRequisicion = idRequisicion;
	}

	@Column(name = "emailAlmacenista", length = 250)
	public String getEmailAlmacenista() {
		return emailAlmacenista;
	}

	public void setEmailAlmacenista(String emailAlmacenista) {
		this.emailAlmacenista = emailAlmacenista;
	}

	@Column(name = "clave", length = 250)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
/*
	@Column(name = "entregarEn", nullable = false)
	public Long getEntregarEn() {
		return entregarEn;
	}

	public void setEntregarEn(Long entregarEn) {
		this.entregarEn = entregarEn;
	}*/

	@Column(name = "fecha", length = 250)
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Column(name = "fecha_entrega", length = 250)
	public Calendar getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Calendar fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Column(name = "fecha_solicitud_proveedor", length = 250)
	public Calendar getFechaSolicitudProveedor() {
		return fechaSolicitudProveedor;
	}

	public void setFechaSolicitudProveedor(Calendar fechaSolicitudProveedor) {
		this.fechaSolicitudProveedor = fechaSolicitudProveedor;
	}

	@Column(name = "email_Personal_Resio_Requisicion", length = 250)
	public String getEmailPersonaRevisoRequisicion() {
		return emailPersonaRevisoRequisicion;
	}

	public void setEmailPersonaRevisoRequisicion(
			String emailPersonaRevisoRequisicion) {
		this.emailPersonaRevisoRequisicion = emailPersonaRevisoRequisicion;
	}

	@Column(name = "email_Personal_Solicitante", length = 250)
	public String getEmailPersonaSolicitante() {
		return emailPersonaSolicitante;
	}

	public void setEmailPersonaSolicitante(String emailPersonaSolicitante) {
		this.emailPersonaSolicitante = emailPersonaSolicitante;
	}

	@Column(name = "status", length = 250)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToOne
	@JoinColumn(name = "proyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@OneToOne
	@JoinColumn(name = "entregarEn")
	public Direccion getEntregarEn() {
		return entregarEn;
	}

	public void setEntregarEn(Direccion entregarEn) {
		this.entregarEn = entregarEn;
	}

	@OneToOne
	@JoinColumn(name = "area")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * Puesto del solicitante
	 * **/
	@OneToOne
	@JoinColumn(name = "posicion")
	public Posicion getPosicion() {
		return posicion;
	}

	/**
	 * Puesto del solicitante
	 * **/
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	@Column
	public String getProg() {
		return prog;
	}

	public void setProg(String prog) {
		this.prog = prog;
	}
	@Column
	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}
	@Column
	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}
	@Column
	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	@Column
	public String getPartidaGenerica() {
		return partidaGenerica;
	}

	public void setPartidaGenerica(String partidaGenerica) {
		this.partidaGenerica = partidaGenerica;
	}
	@Column
	public String getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}
	@Column
	public Long getNumeroInventario() {
		return numeroInventario;
	}

	public void setNumeroInventario(Long numeroInventario) {
		this.numeroInventario = numeroInventario;
	}

	/**
	 * persona solicitante
	 * **/
	@OneToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	/**
	 * persona solicitante
	 * **/
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	@Column
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
}
