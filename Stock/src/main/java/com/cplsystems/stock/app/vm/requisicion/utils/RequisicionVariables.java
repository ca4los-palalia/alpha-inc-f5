/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion.utils;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.ListModel;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class RequisicionVariables extends BasicStructure {

	private static final long serialVersionUID = -5741444614581908156L;
	protected List<RequisicionProducto> requisicionProductos;
	protected RequisicionProducto requisicionProductoSeleccionado;
	protected List<String> productosTemporalModel;
	protected ListModel<String> productosModel;
	protected String productoClaveSelectedItem;
	protected String importeTotal = "0.0";
	protected Integer itemsOnList = 0;

	public RequisicionVariables() {
		requisicionProductos = new ArrayList<RequisicionProducto>();
	}

	public List<RequisicionProducto> getRequisicionProductos() {
		return requisicionProductos;
	}

	public void setRequisicionProductos(
			List<RequisicionProducto> requisicionProductos) {
		this.requisicionProductos = requisicionProductos;
	}

	public RequisicionProducto getRequisicionProductoSeleccionado() {
		return requisicionProductoSeleccionado;
	}

	public void setRequisicionProductoSeleccionado(
			RequisicionProducto requisicionProductoSeleccionado) {
		this.requisicionProductoSeleccionado = requisicionProductoSeleccionado;
	}

	public ListModel<String> getProductosModel() {
		return productosModel;
	}

	public void setProductosModel(ListModel<String> productosModel) {
		this.productosModel = productosModel;
	}

	public String getProductoClaveSelectedItem() {
		return productoClaveSelectedItem;
	}

	public void setProductoClaveSelectedItem(String productoClaveSelectedItem) {
		this.productoClaveSelectedItem = productoClaveSelectedItem;
	}

	public String getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Integer getItemsOnList() {
		return itemsOnList;
	}

	public void setItemsOnList(Integer itemsOnList) {
		this.itemsOnList = itemsOnList;
	}

}
