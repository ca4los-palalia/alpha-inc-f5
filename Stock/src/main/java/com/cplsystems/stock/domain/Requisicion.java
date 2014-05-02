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

@Entity
@Table(name = "Requsicion")
public class Requisicion {

	private Long idRequisicion;
	private String emailAlmacenista;
	private String clave;
	private Long entregarEn;
	private Calendar fecha;
	private Calendar fechaEntrega;
	private Calendar fechaSolicitudProveedor;
	private String emailPersonaRevisoRequisicion;
	private String emailPersonaSolicitante;
	private String status;
	private Proyecto proyecto;
	private Direccion entregaEn;
	
	private String areaSolicitante;
	private Persona solicitante;
	private String puesto;
	private String adscripcion;
	private String justificacion;
	
	

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

	@Column(name = "entregarEn", nullable = false)
	public Long getEntregarEn() {
		return entregarEn;
	}

	public void setEntregarEn(Long entregarEn) {
		this.entregarEn = entregarEn;
	}

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
	@JoinColumn(name = "entregaEn")
	public Direccion getEntregaEn() {
		return entregaEn;
	}

	public void setEntregaEn(Direccion entregaEn) {
		this.entregaEn = entregaEn;
	}
	
	

}
