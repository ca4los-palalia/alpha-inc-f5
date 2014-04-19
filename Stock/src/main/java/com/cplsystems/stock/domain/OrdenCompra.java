/**
 * 
 */
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

@Entity
@Table
public class OrdenCompra implements Serializable {

	private static final long serialVersionUID = 1308611252913327251L;
	
	private Long idOrdenCompra;
	private String codigo;
	private Calendar fechaOrden;
	private Cotizacion cotizacion;


	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrdenCompra() {
		return idOrdenCompra;
	}
	public void setIdOrdenCompra(Long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}
	@Column
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Column
	public Calendar getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Calendar fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	@OneToOne
	@JoinColumn (name = "idCotizacion")
	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	

	/*
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(Long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	@Column(name = "codigo", length = 250)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "fechaOrden", length = 250)
	public Calendar getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Calendar fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
*/
}