/**
 * 
 */
package com.cplsystems.stock.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CuentaPago")
public class CuentaPago {

	
	private Long idCuentaPago;
	private String moneda;
	private String cuentaBancaria;
	private String banco;
	private String sucursal;
	private String aNombreDe;
	private Proveedor proveedor;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCuentasPago", length = 250)
	public Long getIdCuentaPago() {
		return idCuentaPago;
	}

	public void setIdCuentaPago(Long idCuentaPago) {
		this.idCuentaPago = idCuentaPago;
	}

	@Column(name = "moneda", length = 250)
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@Column(name = "cuentaBancaria", length = 250)
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	@Column(name = "banco", length = 250)
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	@Column(name = "sucursal", length = 250)
	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	@Column(name = "aNombreDe", length = 250)
	public String getaNombreDe() {
		return aNombreDe;
	}

	public void setaNombreDe(String aNombreDe) {
		this.aNombreDe = aNombreDe;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
