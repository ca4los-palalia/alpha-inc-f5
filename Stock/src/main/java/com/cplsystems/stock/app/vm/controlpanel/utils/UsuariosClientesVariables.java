/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import java.util.ArrayList;
import java.util.List;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class UsuariosClientesVariables extends BasicStructure {

	private static final long serialVersionUID = 8459358422760322689L;

	protected List<Usuarios> usuarios;
	protected Usuarios usuarioSeleccionado;
	protected Organizacion organizacion;
	protected Boolean privilegioRequision = false;
	protected Boolean privilegioConcentrado = false;
	protected Boolean privilegioCotizacionAutorizacion = false;
	protected Boolean privilegioOrdenCompra = false;
	protected Privilegios toRemove = null;

	public void init() {
		organizacion = (Organizacion) sessionUtils
				.getFromSession(SessionUtils.FIRMA);
		usuarios = usuarioService.getUsuariosByOrganizacion(organizacion);

		usuarioSeleccionado = new Usuarios();
		Persona persona = new Persona();
		Contacto contacto = new Contacto();
		contacto.setEmail(new Email());
		persona.setContacto(contacto);
		usuarioSeleccionado.setPersona(persona);
		usuarioSeleccionado.setOrganizacion(organizacion);

		if (usuarios == null) {
			usuarios = new ArrayList<Usuarios>();
		} else {
			for (Usuarios usuario : usuarios) {
				if (usuario.getPersona().getContacto() != null) {
					usuario.getPersona().setContacto(
							contactoService.getContactoByEmail(usuario
									.getPersona().getContacto().getEmail()));
				} else {
					Email email = new Email();
					contacto = new Contacto();
					contacto.setEmail(email);
					usuario.getPersona().setContacto(contacto);
				}

				usuario.setRetypeKennwort(usuario.getKennwort());
				usuario.setPrivilegios(privilegioService
						.getPrivilegiosByUsuario(usuario));
			}
		}

	}

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuarios getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public Boolean getPrivilegioRequision() {
		return privilegioRequision;
	}

	public void setPrivilegioRequision(Boolean privilegioRequision) {
		this.privilegioRequision = privilegioRequision;
	}

	public Boolean getPrivilegioConcentrado() {
		return privilegioConcentrado;
	}

	public void setPrivilegioConcentrado(Boolean privilegioConcentrado) {
		this.privilegioConcentrado = privilegioConcentrado;
	}

	public Boolean getPrivilegioCotizacionAutorizacion() {
		return privilegioCotizacionAutorizacion;
	}

	public void setPrivilegioCotizacionAutorizacion(
			Boolean privilegioCotizacionAutorizacion) {
		this.privilegioCotizacionAutorizacion = privilegioCotizacionAutorizacion;
	}

	public Boolean getPrivilegioOrdenCompra() {
		return privilegioOrdenCompra;
	}

	public void setPrivilegioOrdenCompra(Boolean privilegioOrdenCompra) {
		this.privilegioOrdenCompra = privilegioOrdenCompra;
	}

}
