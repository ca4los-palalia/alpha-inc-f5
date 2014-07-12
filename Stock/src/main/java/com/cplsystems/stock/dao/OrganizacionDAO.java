/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface OrganizacionDAO {

	void save(Organizacion organizacion);

	void delete(Organizacion organizacion);

	List<Organizacion> getOrganizaciones();

	List<Organizacion> getCompaniasByNombreRFC(String compania, String rfc);

	List<Organizacion> getCompaniasByNombre(String compania);

	List<Organizacion> getCompaniasByRFC(String rfc);

}
