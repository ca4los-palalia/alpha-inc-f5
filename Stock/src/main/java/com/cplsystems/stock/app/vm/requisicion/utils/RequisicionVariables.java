/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.zul.ListModel;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.RequisicionInbox;
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
	protected Date fecha;
	protected Calendar fechaCalendar;
	protected DesgloceTotal desgloceTotal;
	protected boolean disabledBuscadorFolio;
	protected boolean disabledBuscadorArea;
	protected boolean checkBuscarNueva;
	protected boolean checkBuscarCancelada;
	protected boolean checkBuscarEnviada;
	protected boolean checkBuscarAceptada;
	protected List<RequisicionInbox> requisicionesInbox;
	protected RequisicionInbox requisicionInboxSeleccionada;
	protected boolean readOnly = false;

	protected List<CotizacionInbox> cotizacionesInbox;
	protected CotizacionInbox cotizacionInboxSeleccionada;

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

	public Date getFecha() {
		Calendar cal = Calendar.getInstance();
		return fecha = cal.getTime();
	}

	public void setFecha(Date fecha) {
		if (fecha != null) {
			fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(fecha);
		}
		this.fecha = fecha;
	}

	public DesgloceTotal getDesgloceTotal() {
		return desgloceTotal;
	}

	public void setDesgloceTotal(DesgloceTotal desgloceTotal) {
		this.desgloceTotal = desgloceTotal;
	}

	public boolean isDisabledBuscadorFolio() {
		return disabledBuscadorFolio;
	}

	public void setDisabledBuscadorFolio(boolean disabledBuscadorFolio) {
		this.disabledBuscadorFolio = disabledBuscadorFolio;
	}

	public boolean isDisabledBuscadorArea() {
		return disabledBuscadorArea;
	}

	public void setDisabledBuscadorArea(boolean disabledBuscadorArea) {
		this.disabledBuscadorArea = disabledBuscadorArea;
	}

	public boolean isCheckBuscarNueva() {
		return checkBuscarNueva;
	}

	public void setCheckBuscarNueva(boolean checkBuscarNueva) {
		this.checkBuscarNueva = checkBuscarNueva;
	}

	public boolean isCheckBuscarCancelada() {
		return checkBuscarCancelada;
	}

	public void setCheckBuscarCancelada(boolean checkBuscarCancelada) {
		this.checkBuscarCancelada = checkBuscarCancelada;
	}

	public boolean isCheckBuscarEnviada() {
		return checkBuscarEnviada;
	}

	public void setCheckBuscarEnviada(boolean checkBuscarEnviada) {
		this.checkBuscarEnviada = checkBuscarEnviada;
	}

	public boolean isCheckBuscarAceptada() {
		return checkBuscarAceptada;
	}

	public void setCheckBuscarAceptada(boolean checkBuscarAceptada) {
		this.checkBuscarAceptada = checkBuscarAceptada;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public List<RequisicionInbox> getRequisicionesInbox() {
		return requisicionesInbox;
	}

	public void setRequisicionesInbox(List<RequisicionInbox> requisicionesInbox) {
		this.requisicionesInbox = requisicionesInbox;
	}

	public RequisicionInbox getRequisicionInboxSeleccionada() {
		return requisicionInboxSeleccionada;
	}

	public void setRequisicionInboxSeleccionada(
			RequisicionInbox requisicionInboxSeleccionada) {
		this.requisicionInboxSeleccionada = requisicionInboxSeleccionada;
	}

	public List<CotizacionInbox> getCotizacionesInbox() {
		return cotizacionesInbox;
	}

	public void setCotizacionesInbox(List<CotizacionInbox> cotizacionesInbox) {
		this.cotizacionesInbox = cotizacionesInbox;
	}

	public CotizacionInbox getCotizacionInboxSeleccionada() {
		return cotizacionInboxSeleccionada;
	}

	public void setCotizacionInboxSeleccionada(
			CotizacionInbox cotizacionInboxSeleccionada) {
		this.cotizacionInboxSeleccionada = cotizacionInboxSeleccionada;
	}

}
