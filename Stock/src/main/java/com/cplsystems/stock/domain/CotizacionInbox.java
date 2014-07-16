/**
 * 
 */
package com.cplsystems.stock.domain;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Entity
@Table
public class CotizacionInbox implements Serializable {

	public final static String NUEVO = "/images/toolbar/newEmail.png";
	public final static String LEIDO = "/images/toolbar/openedEmail.png";
	private static final long serialVersionUID = -940196661832310329L;
	private Long idCotizacion;
	private Cotizacion cotizacion;
	private Boolean leido;
	private String comentarios;
	private Date fechaRegistro;

	private String icono;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion")
	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	@Column
	public Boolean getLeido() {
		return leido;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}

	@Column
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Transient
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

}
