package com.cplsystems.stock.app.vm.requisicion.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMultipart;

import org.zkoss.zul.ListModel;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;

public class RequisicionVariables extends BasicStructure {
	private static final long serialVersionUID = -5741444614581908156L;
	protected List<RequisicionProducto> requisicionProductos;
	protected RequisicionProducto requisicionProductoSeleccionado;
	protected List<String> productosTemporalModel;
	protected ListModel<String> productosModel;
	protected String productoClaveSelectedItem;
	protected String importeTotal = "0.0";
	protected Integer itemsOnList = Integer.valueOf(0);
	protected Date fecha;
	protected Calendar fechaCalendar;
	protected DesgloceTotal desgloceTotal;
	protected boolean checkBuscarNueva;
	protected boolean checkBuscarCancelada;
	protected boolean checkBuscarEnviada;
	protected boolean checkBuscarAceptada;
	protected List<RequisicionInbox> requisicionesInbox;
	protected RequisicionInbox requisicionInboxSeleccionada;
	protected boolean readOnly = false;
	protected String rutaPdfGenerado;
	protected List<CotizacionInbox> cotizacionesInbox;
	protected CotizacionInbox cotizacionInboxSeleccionada;
	
	public RequisicionVariables() {
		this.requisicionProductos = new ArrayList();
	}

	public List<RequisicionProducto> getRequisicionProductos() {
		return this.requisicionProductos;
	}

	public void setRequisicionProductos(List<RequisicionProducto> requisicionProductos) {
		this.requisicionProductos = requisicionProductos;
	}

	public RequisicionProducto getRequisicionProductoSeleccionado() {
		return this.requisicionProductoSeleccionado;
	}

	public void setRequisicionProductoSeleccionado(RequisicionProducto requisicionProductoSeleccionado) {
		this.requisicionProductoSeleccionado = requisicionProductoSeleccionado;
	}

	public ListModel<String> getProductosModel() {
		return this.productosModel;
	}

	public void setProductosModel(ListModel<String> productosModel) {
		this.productosModel = productosModel;
	}

	public String getProductoClaveSelectedItem() {
		return this.productoClaveSelectedItem;
	}

	public void setProductoClaveSelectedItem(String productoClaveSelectedItem) {
		this.productoClaveSelectedItem = productoClaveSelectedItem;
	}

	public String getImporteTotal() {
		return this.importeTotal;
	}

	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Integer getItemsOnList() {
		return this.itemsOnList;
	}

	public void setItemsOnList(Integer itemsOnList) {
		this.itemsOnList = itemsOnList;
	}

	public Date getFecha() {
		Calendar cal = Calendar.getInstance();
		return this.fecha = cal.getTime();
	}

	public void setFecha(Date fecha) {
		if (fecha != null) {
			this.fechaCalendar = Calendar.getInstance();
			this.fechaCalendar.setTime(fecha);
		}
		this.fecha = fecha;
	}

	public DesgloceTotal getDesgloceTotal() {
		return this.desgloceTotal;
	}

	public void setDesgloceTotal(DesgloceTotal desgloceTotal) {
		this.desgloceTotal = desgloceTotal;
	}

	public boolean isCheckBuscarNueva() {
		return this.checkBuscarNueva;
	}

	public void setCheckBuscarNueva(boolean checkBuscarNueva) {
		this.checkBuscarNueva = checkBuscarNueva;
	}

	public boolean isCheckBuscarCancelada() {
		return this.checkBuscarCancelada;
	}

	public void setCheckBuscarCancelada(boolean checkBuscarCancelada) {
		this.checkBuscarCancelada = checkBuscarCancelada;
	}

	public boolean isCheckBuscarEnviada() {
		return this.checkBuscarEnviada;
	}

	public void setCheckBuscarEnviada(boolean checkBuscarEnviada) {
		this.checkBuscarEnviada = checkBuscarEnviada;
	}

	public boolean isCheckBuscarAceptada() {
		return this.checkBuscarAceptada;
	}

	public void setCheckBuscarAceptada(boolean checkBuscarAceptada) {
		this.checkBuscarAceptada = checkBuscarAceptada;
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public List<RequisicionInbox> getRequisicionesInbox() {
		return this.requisicionesInbox;
	}

	public void setRequisicionesInbox(List<RequisicionInbox> requisicionesInbox) {
		this.requisicionesInbox = requisicionesInbox;
	}

	public RequisicionInbox getRequisicionInboxSeleccionada() {
		return this.requisicionInboxSeleccionada;
	}

	public void setRequisicionInboxSeleccionada(RequisicionInbox requisicionInboxSeleccionada) {
		this.requisicionInboxSeleccionada = requisicionInboxSeleccionada;
	}

	public List<CotizacionInbox> getCotizacionesInbox() {
		return this.cotizacionesInbox;
	}

	public void setCotizacionesInbox(List<CotizacionInbox> cotizacionesInbox) {
		this.cotizacionesInbox = cotizacionesInbox;
	}

	public CotizacionInbox getCotizacionInboxSeleccionada() {
		return this.cotizacionInboxSeleccionada;
	}

	public void setCotizacionInboxSeleccionada(CotizacionInbox cotizacionInboxSeleccionada) {
		this.cotizacionInboxSeleccionada = cotizacionInboxSeleccionada;
	}
}
