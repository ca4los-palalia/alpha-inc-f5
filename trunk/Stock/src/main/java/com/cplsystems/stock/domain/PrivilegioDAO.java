/**
 * 
 */
package com.cplsystems.stock.domain;

import java.util.List;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface PrivilegioDAO {

	void save(Privilegios privilegios);

	void delete(Privilegios privilegios);

	List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios);

}
