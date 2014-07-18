/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.PrivilegioDAO;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class PrivilegioService {

	@Autowired
	private PrivilegioDAO privilegioDAO;

	public void save(final Privilegios privilegios) {
		privilegioDAO.save(privilegios);
	}

	public void delete(final Privilegios privilegios) {
		privilegioDAO.delete(privilegios);
	}

	public List<Privilegios> getPrivilegiosByUsuario(final Usuarios usuarios) {
		return privilegioDAO.getPrivilegiosByUsuario(usuarios);
	}

	public List<Privilegios> getUsuariosByPrivilegio(
			UserPrivileges cotizarAutorizar) {
		return privilegioDAO.getUsuariosByPrivilegio(cotizarAutorizar);
	}
}
