/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.OrdenCompraInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class OrdenCompraVariables extends BasicStructure {

	private static final long serialVersionUID = -5741444614581908156L;
	protected List<OrdenCompraInbox> ordenesCompraInbox;
	protected OrdenCompraInbox ordenCompraInboxSeleccionada;
	protected Date fecha;
	protected Calendar fechaCalendar;
	protected boolean checkBuscarNueva;
	protected boolean checkBuscarCancelada;
	protected boolean checkBuscarEnviada;
	protected boolean checkBuscarAceptada;

	public List<OrdenCompraInbox> getOrdenesCompraInbox() {
		return ordenesCompraInbox;
	}

	public void setOrdenesCompraInbox(List<OrdenCompraInbox> ordenesCompraInbox) {
		this.ordenesCompraInbox = ordenesCompraInbox;
	}

	public OrdenCompraInbox getOrdenCompraInboxSeleccionada() {
		return ordenCompraInboxSeleccionada;
	}

	public void setOrdenCompraInboxSeleccionada(
			OrdenCompraInbox ordenCompraInboxSeleccionada) {
		this.ordenCompraInboxSeleccionada = ordenCompraInboxSeleccionada;
	}
	
	public Calendar getFechaCalendar() {
		return fechaCalendar;
	}

	public void setFechaCalendar(Calendar fechaCalendar) {
		this.fechaCalendar = fechaCalendar;
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
	
}
