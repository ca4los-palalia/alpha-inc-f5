package com.cplsystems.stock.app.vm.requisicion;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ControlVariables extends BasicStructure {
	private static final long serialVersionUID = -5741444614581908156L;
	protected List<OrdenCompraInbox> ordenesCompraInbox;
	protected OrdenCompraInbox ordenCompraInboxSeleccionada;
	protected Date fecha;
	protected Calendar fechaCalendar;
	protected boolean checkBuscarNueva;
	protected boolean checkBuscarCancelada;
	protected boolean checkBuscarEnviada;
	protected boolean checkBuscarAceptada;
	protected Float precioTotal;
	protected Integer numeroProductos;
	protected Integer productosrestantes;

	public List<OrdenCompraInbox> getOrdenesCompraInbox() {
		return this.ordenesCompraInbox;
	}

	public void setOrdenesCompraInbox(List<OrdenCompraInbox> ordenesCompraInbox) {
		this.ordenesCompraInbox = ordenesCompraInbox;
	}

	public OrdenCompraInbox getOrdenCompraInboxSeleccionada() {
		return this.ordenCompraInboxSeleccionada;
	}

	public void setOrdenCompraInboxSeleccionada(OrdenCompraInbox ordenCompraInboxSeleccionada) {
		this.ordenCompraInboxSeleccionada = ordenCompraInboxSeleccionada;
	}

	public Calendar getFechaCalendar() {
		return this.fechaCalendar;
	}

	public void setFechaCalendar(Calendar fechaCalendar) {
		this.fechaCalendar = fechaCalendar;
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

	public Float getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Integer getNumeroProductos() {
		return this.numeroProductos;
	}

	public void setNumeroProductos(Integer numeroProductos) {
		this.numeroProductos = numeroProductos;
	}

	public Integer getProductosrestantes() {
		return productosrestantes;
	}

	public void setProductosrestantes(Integer productosrestantes) {
		this.productosrestantes = productosrestantes;
	}
	
}
