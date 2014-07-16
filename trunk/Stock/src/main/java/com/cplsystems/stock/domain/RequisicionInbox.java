/**
 * 
 */
package com.cplsystems.stock.domain;

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
import javax.persistence.Transient;

import org.zkoss.bind.annotation.Command;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Entity
@Table
public class RequisicionInbox implements Serializable {

	public final static String NUEVO = "/images/toolbar/newEmail.png";
	public final static String LEIDO = "/images/toolbar/openedEmail.png";
	private static final long serialVersionUID = -9044125899772985818L;
	private Long idRequsicionInbox;
	private Requisicion requisicion;
	private Boolean leido;
	private Date fechaRegistro;
	private String comentarios;

	private String icono;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdRequsicionInbox() {
		return idRequsicionInbox;
	}

	public void setIdRequsicionInbox(Long idRequsicionInbox) {
		this.idRequsicionInbox = idRequsicionInbox;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@Column
	public Boolean getLeido() {
		return leido;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Command
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Transient
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

}
