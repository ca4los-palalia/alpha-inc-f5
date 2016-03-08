package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.UsuarioDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuarios getUsuarioByCredentials(String usuario, String password) throws DataAccessException {
		return this.usuarioDAO.getUsuarioByCredentials(usuario, password);
	}

	public void save(Usuarios usuarios) {
		this.usuarioDAO.save(usuarios);
	}

	public void delete(Usuarios usuario) {
		this.usuarioDAO.delete(usuario);
	}

	public List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion) {
		return this.usuarioDAO.getUsuariosByOrganizacion(organizacion);
	}

	public boolean verificarNombreUsuario(String benutzer, Long idUsuario) {
		return this.usuarioDAO.verificarNombreUsuario(benutzer, idUsuario);
	}

	public Usuarios getClienteByOrganizacion(Organizacion organizacion) {
		return this.usuarioDAO.getClienteByOrganizacion(organizacion);
	}

	public Usuarios getOwner(Organizacion organizacion) {
		return this.usuarioDAO.getOwner(organizacion);
	}

	public List<Usuarios> getUsuariosByOrganizacionAll(Organizacion organizacion) {
		return this.usuarioDAO.getUsuariosByOrganizacionAll(organizacion);
	}

	public List<Usuarios> getAll() {
		return this.usuarioDAO.getAll();
	}
}
