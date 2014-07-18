/**
 * 
 */
package com.cplsystems.stock.domain;

import java.util.List;

import com.cplsystems.stock.app.utils.UserPrivileges;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface PrivilegioDAO {

	void save(Privilegios privilegios);

	void delete(Privilegios privilegios);

	List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios);

	List<Privilegios> getUsuariosByPrivilegio(UserPrivileges cotizarAutorizar);

}
