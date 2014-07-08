/**
 * 
 */
package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public interface UsuarioDAO {

	/**
	 * @param usuario
	 * @param password
	 * @return
	 */
	Usuarios getUsuarioByCredentials(String usuario, String password);

}
