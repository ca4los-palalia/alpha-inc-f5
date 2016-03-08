package com.cplsystems.stock.app.vm.controlpanel.utils;

import java.io.File;
import java.util.List;

import org.zkoss.image.Image;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

public class UsuarioVariables extends BasicStructure {
	private static final long serialVersionUID = 1814036749677443421L;
	protected Organizacion organizacion;
	protected Image businessImage;
	protected byte[] imageInBytes;
	protected String imageFormat;
	protected Usuarios usuario;
	protected Usuarios usuarioSeleccionado;
	protected List<Usuarios> usuarios;
	protected boolean deshabiliraRadioButton;
	protected File picture;

	public void init() {
		this.organizacion = new Organizacion();
		this.usuario = new Usuarios();
		this.usuarioSeleccionado = new Usuarios();
		this.usuario.setOrganizacion(this.organizacion);
		this.deshabiliraRadioButton = false;
	}

	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Image getBusinessImage() {
		return this.businessImage;
	}

	public void setBusinessImage(Image businessImage) {
		this.businessImage = businessImage;
	}

	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public List<Usuarios> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuarios getUsuarioSeleccionado() {
		return this.usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public boolean isDeshabiliraRadioButton() {
		return this.deshabiliraRadioButton;
	}

	public void setDeshabiliraRadioButton(boolean deshabiliraRadioButton) {
		this.deshabiliraRadioButton = deshabiliraRadioButton;
	}
}
