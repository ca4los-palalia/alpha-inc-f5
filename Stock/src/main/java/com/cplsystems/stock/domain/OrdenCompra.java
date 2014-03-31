/**
 * 
 */
package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrdenCompra")
public class OrdenCompra {

	private Long idOrdenCompra;
	private String codigo;
	private Calendar fechaOrden;
	private Cotizacion cotizacion;

	@Id
	@Column(name = "idOrdenCompra", nullable = false)
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


	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion")
	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

}
