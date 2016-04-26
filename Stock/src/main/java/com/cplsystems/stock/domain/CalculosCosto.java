package com.cplsystems.stock.domain;

import java.io.Serializable;
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
public class CalculosCosto implements Serializable {

	private static final long serialVersionUID = 1573034195911041468L;
	private Long idCalculosCosto;
	private String valor1;
	private String valor2;
	private String valor3;
	private boolean activo;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCalculosCosto() {
		return idCalculosCosto;
	}

	public void setIdCalculosCosto(Long idCalculosCosto) {
		this.idCalculosCosto = idCalculosCosto;
	}

	@Column
	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	@Column
	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	@Column
	public String getValor3() {
		return valor3;
	}

	public void setValor3(String valor3) {
		this.valor3 = valor3;
	}

	@Column
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}
