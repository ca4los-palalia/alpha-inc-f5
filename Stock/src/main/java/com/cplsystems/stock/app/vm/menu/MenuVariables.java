package com.cplsystems.stock.app.vm.menu;

import com.cplsystems.stock.app.vm.BasicStructure;
import java.util.Map;

public class MenuVariables extends BasicStructure {

	private static final long serialVersionUID = 3599338863971941993L;
	
	protected String PAGE_TO_RENDER;
	protected Map<String, Object> args;
	protected boolean mostrarRequisiones;
	protected boolean mostrarConcentrados;
	protected boolean mostrarCotizacionesAutorizaciones;
	protected boolean mostrarOrdenesCompra;
	protected boolean mostrarPanelControl;
	protected boolean ownerOptions;
	protected boolean clientOptions;
	
	public boolean isMostrarRequisiones() {
		return mostrarRequisiones;
	}
	public void setMostrarRequisiones(boolean mostrarRequisiones) {
		this.mostrarRequisiones = mostrarRequisiones;
	}
	public boolean isMostrarConcentrados() {
		return mostrarConcentrados;
	}
	public void setMostrarConcentrados(boolean mostrarConcentrados) {
		this.mostrarConcentrados = mostrarConcentrados;
	}
	public boolean isMostrarCotizacionesAutorizaciones() {
		return mostrarCotizacionesAutorizaciones;
	}
	public void setMostrarCotizacionesAutorizaciones(boolean mostrarCotizacionesAutorizaciones) {
		this.mostrarCotizacionesAutorizaciones = mostrarCotizacionesAutorizaciones;
	}
	public boolean isMostrarOrdenesCompra() {
		return mostrarOrdenesCompra;
	}
	public void setMostrarOrdenesCompra(boolean mostrarOrdenesCompra) {
		this.mostrarOrdenesCompra = mostrarOrdenesCompra;
	}
	public boolean isMostrarPanelControl() {
		return mostrarPanelControl;
	}
	public void setMostrarPanelControl(boolean mostrarPanelControl) {
		this.mostrarPanelControl = mostrarPanelControl;
	}
	public boolean isOwnerOptions() {
		return ownerOptions;
	}
	public void setOwnerOptions(boolean ownerOptions) {
		this.ownerOptions = ownerOptions;
	}
	public boolean isClientOptions() {
		return clientOptions;
	}
	public void setClientOptions(boolean clientOptions) {
		this.clientOptions = clientOptions;
	}
	
	
	
	
}
