/**
 * 
 */
package com.cplsystems.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.dao.UsuarioDAO;
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
		return usuarioDAO.getUsuarioByCredentials(usuario,
				password);
		/*
		return usuarioDAO.getUsuarioByCredentials(StockUtils.encrypt(usuario),
				StockUtils.encrypt(password));*/
	}
}
