package com.cplsystems.stock.domain;


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
public class KardexProveedor{
	
	private Long idKardexProveedor;
	private Usuarios usuario;
	private Organizacion organizacion;
	private EstatusRequisicion estatusRequisicion;
	private Proveedor proveedor;
	private OrdenCompra ordenCompra;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdKardexProveedor() {
		return this.idKardexProveedor;
	}
	public void setIdKardexProveedor(Long idKardexProveedor) {
		this.idKardexProveedor = idKardexProveedor;
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
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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
	@JoinColumn(name = "ordenCompra")
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	
	
}
