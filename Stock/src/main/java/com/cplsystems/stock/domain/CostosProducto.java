package com.cplsystems.stock.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class CostosProducto {

	private Long idCostosProducto;
	private Float reposicionUnitario;
	private Float reposicionActualizado;
	private Float maximoUnitario;
	private Float maximoActualizado;
	private Calendar reposicionFecha;
	private Calendar maximoFecha;
	private Float costoPromedio;
	private Float costoUltimo;
	private Calendar costoUltimoFecha;
	private Float costoCapa;
	private Calendar costoCapaFecha;
	private Producto producto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCostosProducto() {
		return idCostosProducto;
	}

	public void setIdCostosProducto(Long idCostosProducto) {
		this.idCostosProducto = idCostosProducto;
	}
	@Column
	public Float getReposicionUnitario() {
		return reposicionUnitario;
	}

	public void setReposicionUnitario(Float reposicionUnitario) {
		this.reposicionUnitario = reposicionUnitario;
	}
	@Column
	public Float getReposicionActualizado() {
		return reposicionActualizado;
	}

	public void setReposicionActualizado(Float reposicionActualizado) {
		this.reposicionActualizado = reposicionActualizado;
	}
	@Column
	public Float getMaximoUnitario() {
		return maximoUnitario;
	}

	public void setMaximoUnitario(Float maximoUnitario) {
		this.maximoUnitario = maximoUnitario;
	}
	@Column
	public Float getMaximoActualizado() {
		return maximoActualizado;
	}

	public void setMaximoActualizado(Float maximoActualizado) {
		this.maximoActualizado = maximoActualizado;
	}
	@Column
	public Calendar getReposicionFecha() {
		return reposicionFecha;
	}

	public void setReposicionFecha(Calendar reposicionFecha) {
		this.reposicionFecha = reposicionFecha;
	}
	@Column
	public Calendar getMaximoFecha() {
		return maximoFecha;
	}

	public void setMaximoFecha(Calendar maximoFecha) {
		this.maximoFecha = maximoFecha;
	}
	@Column
	public Float getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Float costoPromedio) {
		this.costoPromedio = costoPromedio;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Column
	public Float getCostoUltimo() {
		return costoUltimo;
	}

	public void setCostoUltimo(Float costoUltimo) {
		this.costoUltimo = costoUltimo;
	}

	@Column
	public Calendar getCostoUltimoFecha() {
		return costoUltimoFecha;
	}

	public void setCostoUltimoFecha(Calendar costoUltimoFecha) {
		this.costoUltimoFecha = costoUltimoFecha;
	}

	@Column
	public Float getCostoCapa() {
		return costoCapa;
	}

	public void setCostoCapa(Float costoCapa) {
		this.costoCapa = costoCapa;
	}

	@Column
	public Calendar getCostoCapaFecha() {
		return costoCapaFecha;
	}

	public void setCostoCapaFecha(Calendar costoCapaFecha) {
		this.costoCapaFecha = costoCapaFecha;
	}
	
}
