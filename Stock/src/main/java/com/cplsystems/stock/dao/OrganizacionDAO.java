/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public interface OrganizacionDAO {

	void save(Organizacion organizacion);

	void delete(Organizacion organizacion);


	List<Organizacion> getOrganizaciones();

	Organizacion getOrganizacionByUsuario(Usuarios usuario);

}
