/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.ModulosOrganizacion;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface ModulosOrganizacionDAO {

	void delete(ModulosOrganizacion modulosOrganizacion);

	List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion organizacion);

	void save(ModulosOrganizacion modulosOrganizacion);

}
