/**
 * 
 */
package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Usuario;

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
	Usuario getUsuarioByCredentials(String usuario, String password);

}
