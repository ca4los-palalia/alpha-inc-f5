/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.UsuarioDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuarios getUsuarioByCredentials(final String usuario,
			final String password) throws DataAccessException {
		return usuarioDAO.getUsuarioByCredentials(usuario, password);
	}

	public void save(final Usuarios usuarios) {
		usuarioDAO.save(usuarios);
	}

	public void delete(final Usuarios usuario) {
		usuarioDAO.delete(usuario);
	}

	public List<Usuarios> getUsuariosByOrganizacion(
			final Organizacion organizacion) {
		return usuarioDAO.getUsuariosByOrganizacion(organizacion);
	}

	public boolean verificarNombreUsuario(final String benutzer,
			final Long idUsuario) {
		return usuarioDAO.verificarNombreUsuario(benutzer, idUsuario);
	}

}
