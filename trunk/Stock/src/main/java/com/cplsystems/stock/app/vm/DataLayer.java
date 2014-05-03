/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.io.Serializable;
import java.util.List;

import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class DataLayer implements Serializable {

	private static final long serialVersionUID = -828756372536148348L;
	protected Producto producto;
	protected Requisicion requisicion;
	protected Municipio municipio;
	protected Estado estado;
	protected Pais pais;
	protected Contrato contrato;
	protected Proveedor proveedorSelected;	
	protected List<Proveedor> proveedoresLista;

	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public List<Proveedor> getProveedoresLista() {
		return proveedoresLista;
	}
	public void setProveedoresLista(List<Proveedor> proveedoresLista) {
		this.proveedoresLista = proveedoresLista;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public Proveedor getProveedorSelected() {
		return proveedorSelected;
	}
	public void setProveedorSelected(Proveedor proveedorSelected) {
		this.proveedorSelected = proveedorSelected;
	}
	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

}
