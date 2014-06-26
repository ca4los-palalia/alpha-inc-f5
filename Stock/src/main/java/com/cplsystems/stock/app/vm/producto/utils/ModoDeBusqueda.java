package com.cplsystems.stock.app.vm.producto.utils;

public class ModoDeBusqueda {

	
	private boolean tipoFamilia;
	private boolean tipoPersonalizado;
	
	private boolean ocultarFamilia;
	private boolean ocultarPersonalizado;
	
	public boolean isTipoFamilia() {
		return tipoFamilia;
	}
	public void setTipoFamilia(boolean tipoFamilia) {
		this.tipoFamilia = tipoFamilia;
	}
	public boolean isTipoPersonalizado() {
		return tipoPersonalizado;
	}
	public void setTipoPersonalizado(boolean tipoPersonalizado) {
		this.tipoPersonalizado = tipoPersonalizado;
	}
	public boolean isOcultarFamilia() {
		return ocultarFamilia;
	}
	public void setOcultarFamilia(boolean ocultarFamilia) {
		this.ocultarFamilia = ocultarFamilia;
	}
	public boolean isOcultarPersonalizado() {
		return ocultarPersonalizado;
	}
	public void setOcultarPersonalizado(boolean ocultarPersonalizado) {
		this.ocultarPersonalizado = ocultarPersonalizado;
	}
	
	
	
	
}
