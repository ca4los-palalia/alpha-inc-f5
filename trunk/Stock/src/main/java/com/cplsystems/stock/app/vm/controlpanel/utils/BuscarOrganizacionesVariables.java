/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import java.util.ArrayList;
import java.util.List;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class BuscarOrganizacionesVariables extends BasicStructure {

	private static final long serialVersionUID = 4490601495632254008L;
	protected String compania;
	protected String rfc;

	protected List<Organizacion> organizaciones;
	protected Organizacion organizacionSeleccionada;

	public void init() {
		organizaciones = new ArrayList<Organizacion>();
		organizacionSeleccionada = new Organizacion();
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public Organizacion getOrganizacionSeleccionada() {
		return organizacionSeleccionada;
	}

	public void setOrganizacionSeleccionada(
			Organizacion organizacionSeleccionada) {
		this.organizacionSeleccionada = organizacionSeleccionada;
	}

}
