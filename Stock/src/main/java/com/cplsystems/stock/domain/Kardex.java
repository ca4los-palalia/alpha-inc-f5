package com.cplsystems.stock.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplsystems.stock.app.utils.StockUtils;

@Entity
@Table
public class Kardex {
	private Long idKardex;
	private Date fechaEntrada;
	
	
	private String factura;
	private Integer entradaCantidad;
	private Integer entradaLote;
	private Integer salidaCantidad;
	private Integer salidaLote;
	private Integer existenciaCantidad;
	private Integer existenciaLote;
	
	private Integer costoPromedio;
	private Float debe;
	private Float haber;
	private Float saldo;
	private boolean botonBuscarProducto;
	private String icon;
	
	private Usuarios usuario;
	private Organizacion organizacion;
	private EstatusRequisicion estatusRequisicion;
	private Producto producto;
	private KardexProveedor kardexProveedor;
	private boolean conf;
	
	private List<AlmacenEntrada> almacenEntradaList;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdKardex() {
		return this.idKardex;
	}
	public void setIdKardex(Long idKardex) {
		this.idKardex = idKardex;
	}
	
	@Column
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
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
	
	
	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@Column
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	
	@Column
	public Integer getEntradaCantidad() {
		return entradaCantidad;
	}
	public void setEntradaCantidad(Integer entradaCantidad) {
		this.entradaCantidad = entradaCantidad;
	}
	
	@Column
	public Integer getEntradaLote() {
		return entradaLote;
	}
	public void setEntradaLote(Integer entradaLote) {
		this.entradaLote = entradaLote;
	}
	
	@Column
	public Integer getSalidaCantidad() {
		return salidaCantidad;
	}
	public void setSalidaCantidad(Integer salidaCantidad) {
		this.salidaCantidad = salidaCantidad;
	}
	
	@Column
	public Integer getSalidaLote() {
		return salidaLote;
	}
	public void setSalidaLote(Integer salidaLote) {
		this.salidaLote = salidaLote;
	}
	
	@Column
	public Integer getExistenciaCantidad() {
		return existenciaCantidad;
	}
	public void setExistenciaCantidad(Integer existenciaCantidad) {
		this.existenciaCantidad = existenciaCantidad;
	}
	
	@Column
	public Integer getExistenciaLote() {
		return existenciaLote;
	}
	public void setExistenciaLote(Integer existenciaLote) {
		this.existenciaLote = existenciaLote;
	}
	
	@Column
	public Integer getCostoPromedio() {
		return costoPromedio;
	}
	public void setCostoPromedio(Integer costoPromedio) {
		this.costoPromedio = costoPromedio;
	}
	
	@Column
	public Float getDebe() {
		return debe;
	}
	public void setDebe(Float debe) {
		this.debe = debe;
	}
	
	@Column
	public Float getHaber() {
		return haber;
	}
	public void setHaber(Float haber) {
		this.haber = haber;
	}
	
	@Column
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	
	@Transient
	public boolean isBotonBuscarProducto() {
		return botonBuscarProducto;
	}
	public void setBotonBuscarProducto(boolean botonBuscarProducto) {
		this.botonBuscarProducto = botonBuscarProducto;
	}
	
	@Column
	public String getIcon() {
		if(icon != null)
			try {
				String des = new StockUtils().Desencriptar(icon);
				if(!des.equals(""))
					icon = des;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@OneToOne
	@JoinColumn(name = "estatusRequisicion")
	public EstatusRequisicion getEstatusRequisicion() {
		return this.estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}
	
	@OneToOne
	@JoinColumn(name = "kardexProveedor")
	public KardexProveedor getKardexProveedor() {
		return kardexProveedor;
	}
	public void setKardexProveedor(KardexProveedor kardexProveedor) {
		this.kardexProveedor = kardexProveedor;
	}
	@Transient
	public List<AlmacenEntrada> getAlmacenEntradaList() {
		return almacenEntradaList;
	}

	public void setAlmacenEntradaList(List<AlmacenEntrada> almacenEntradaList) {
		this.almacenEntradaList = almacenEntradaList;
	}
	
	@Column
	public boolean isConf() {
		return conf;
	}
	public void setConf(boolean conf) {
		this.conf = conf;
	}
	
}
