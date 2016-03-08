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
	private Producto producto;
	private Requisicion requisicion;
	private EstatusRequisicion estatusRequisicion;
	private RequisicionProducto requisicionProducto;
	private String excelFile;
	private String cancelarDescripcion;
	private String folioCotizacion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private Almacen almacen;

	@Id
	@Column(name = "idcotizacion", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCotizacion() {
		return this.idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	@Column(name = "detallesExtras", length = 250)
	public String getDetallesExtras() {
		return this.detallesExtras;
	}

	public void setDetallesExtras(String detallesExtras) {
		this.detallesExtras = detallesExtras;
	}

	@Column(name = "fechaEnvioCotizacion", length = 250)
	public Calendar getFechaEnvioCotizacion() {
		return this.fechaEnvioCotizacion;
	}

	public void setFechaEnvioCotizacion(Calendar fechaEnvioCotizacion) {
		this.fechaEnvioCotizacion = fechaEnvioCotizacion;
	}

	@Column(name = "fechaResolucion", length = 250)
	public Calendar getFechaResolucion() {
		return this.fechaResolucion;
	}

	public void setFechaResolucion(Calendar fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	@Column(name = "impuestos", length = 250)
	public Float getImpuestos() {
		return this.impuestos;
	}

	public void setImpuestos(Float impuestos) {
		this.impuestos = impuestos;
	}

	@Column(name = "retencion", length = 250)
	public Float getRetencion() {
		return this.retencion;
	}

	public void setRetencion(Float retencion) {
		this.retencion = retencion;
	}

	@Column(name = "subTotal", length = 250)
	public Float getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	@Column(name = "total", length = 250)
	public Float getTotal() {
		return this.total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Column(name = "extras", length = 250)
	public Float getExtras() {
		return this.extras;
	}

	public void setExtras(Float extras) {
		this.extras = extras;
	}

	@OneToOne
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@OneToOne
	@JoinColumn(name = "requisicion")
	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@Column
	public Integer getStatusPago() {
		return this.statusPago;
	}

	public void setStatusPago(Integer statusPago) {
		this.statusPago = statusPago;
	}

	@OneToOne
	@JoinColumn(name = "estatusRequisicion")
	public EstatusRequisicion getEstatusRequisicion() {
		return this.estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}

	@Column
	public String getFolioCotizacion() {
		return this.folioCotizacion;
	}

	public void setFolioCotizacion(String folioCotizacion) {
		this.folioCotizacion = folioCotizacion;
	}

	@Column
	public String getExcelFile() {
		return this.excelFile;
	}

	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
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

	@Column
	public String getCancelarDescripcion() {
		return this.cancelarDescripcion;
	}

	public void setCancelarDescripcion(String cancelarDescripcion) {
		this.cancelarDescripcion = cancelarDescripcion;
	}

	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@OneToOne
	@JoinColumn(name = "requisicionProducto")
	public RequisicionProducto getRequisicionProducto() {
		return this.requisicionProducto;
	}

	public void setRequisicionProducto(RequisicionProducto requisicionProducto) {
		this.requisicionProducto = requisicionProducto;
	}

	@OneToOne
	@JoinColumn(name = "almacen")
	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
}
