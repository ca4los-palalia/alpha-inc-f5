/**
 * 
 */
package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cotizacion")
public class Cotizacion {

	private Long idCotizacion;
	private String detallesExtras;
	private Calendar fechaEnvioCotizacion;
	private Calendar fechaResolucion;
	private Float impuestos;
	private Float retencion;
	private Integer statusPago;
	private Float subTotal;
	private Float total;
	private Float extras;
	private Proveedor proveedor;
	private Requisicion requisicion;
	private EstatusRequisicion estatusRequisicion;
	private String excelFile;
	
	private String folioCotizacion;

	@Id
	@Column(name = "idcotizacion", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	@Column(name = "detallesExtras", length = 250)
	public String getDetallesExtras() {
		return detallesExtras;
	}

	public void setDetallesExtras(String detallesExtras) {
		this.detallesExtras = detallesExtras;
	}

	@Column(name = "fechaEnvioCotizacion", length = 250)
	public Calendar getFechaEnvioCotizacion() {
		return fechaEnvioCotizacion;
	}

	public void setFechaEnvioCotizacion(Calendar fechaEnvioCotizacion) {
		this.fechaEnvioCotizacion = fechaEnvioCotizacion;
	}

	@Column(name = "fechaResolucion", length = 250)
	public Calendar getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Calendar fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	@Column(name = "impuestos", length = 250)
	public Float getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Float impuestos) {
		this.impuestos = impuestos;
	}

	@Column(name = "retencion", length = 250)
	public Float getRetencion() {
		return retencion;
	}

	public void setRetencion(Float retencion) {
		this.retencion = retencion;
	}

	@Column(name = "subTotal", length = 250)
	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	@Column(name = "total", length = 250)
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Column(name = "extras", length = 250)
	public Float getExtras() {
		return extras;
	}

	public void setExtras(Float extras) {
		this.extras = extras;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}
	@Column
	public Integer getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(Integer statusPago) {
		this.statusPago = statusPago;
	}
	@OneToOne
	@JoinColumn(name = "estatusRequisicion")
	public EstatusRequisicion getEstatusRequisicion() {
		return estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}

	@Column
	public String getFolioCotizacion() {
		return folioCotizacion;
	}

	public void setFolioCotizacion(String folioCotizacion) {
		this.folioCotizacion = folioCotizacion;
	}

	@Column
	public String getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}
	
}
