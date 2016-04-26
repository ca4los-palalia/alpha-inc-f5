package com.cplsystems.stock.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class CotizacionEnvios implements Serializable {
	private static final long serialVersionUID = -2306811750622232942L;
	private Long idCotizacionEnvios;
	private Area area;
	private Cotizacion cotizacion;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCotizacionEnvios() {
		return this.idCotizacionEnvios;
	}

	public void setIdCotizacionEnvios(Long idCotizacionEnvios) {
		this.idCotizacionEnvios = idCotizacionEnvios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idArea")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCotizacion")
	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
}
