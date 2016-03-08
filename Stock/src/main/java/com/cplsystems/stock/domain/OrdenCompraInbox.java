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

@Entity
@Table
public class OrdenCompraInbox implements Serializable {
	public static final String NUEVO = "/images/toolbar/newEmail.png";
	public static final String LEIDO = "/images/toolbar/openedEmail.png";
	private static final long serialVersionUID = -4541521928529012130L;
	private Long idOrdenCompraInbox;
	private OrdenCompra ordenCompra;
	private Boolean leido;
	private Date fechaCreacion;
	private String comentarios;
	private String icono;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrdenCompraInbox() {
		return this.idOrdenCompraInbox;
	}

	public void setIdOrdenCompraInbox(Long idOrdenCompraInbox) {
		this.idOrdenCompraInbox = idOrdenCompraInbox;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordenCompra")
	public OrdenCompra getOrdenCompra() {
		return this.ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	@Column
	public Boolean getLeido() {
		return this.leido;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Transient
	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	@Column
	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
