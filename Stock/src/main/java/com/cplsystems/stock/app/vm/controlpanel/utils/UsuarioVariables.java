/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import org.zkoss.image.Image;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class UsuarioVariables extends BasicStructure {

	private static final long serialVersionUID = 1814036749677443421L;
	protected Organizacion organizacion;
	protected Image businessImage;
	protected byte[] imageInBytes;
	protected String imageFormat;
	protected Usuarios usuario;

	public void init() {
		organizacion = new Organizacion();
		usuario = new Usuarios();
		usuario.setOrganizacion(organizacion);
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Image getBusinessImage() {
		return businessImage;
	}

	public void setBusinessImage(Image businessImage) {
		this.businessImage = businessImage;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

}
