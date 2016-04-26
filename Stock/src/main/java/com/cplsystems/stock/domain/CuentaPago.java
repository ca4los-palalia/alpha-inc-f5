package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class CuentaPago {
	private Long idCuentaPago;
	private String cuentaBancaria;
	private String sucursal;
	private String aNombreDe;
	private Proveedor proveedor;
	private Banco banco;
	private Moneda moneda;
	private String clabe;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCuentaPago() {
		return this.idCuentaPago;
	}

	public void setIdCuentaPago(Long idCuentaPago) {
		this.idCuentaPago = idCuentaPago;
	}

	@Column(name = "cuentaBancaria", length = 250)
	public String getCuentaBancaria() {
		return this.cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	@Column(name = "sucursal", length = 250)
	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	@Column(name = "aNombreDe", length = 250)
	public String getaNombreDe() {
		return this.aNombreDe;
	}

	public void setaNombreDe(String aNombreDe) {
		this.aNombreDe = aNombreDe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "banco")
	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moneda")
	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	@Column
	public String getClabe() {
		return this.clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
}
