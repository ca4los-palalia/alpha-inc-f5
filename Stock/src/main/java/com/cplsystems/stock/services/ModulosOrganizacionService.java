/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ModulosOrganizacionDAO;
import com.cplsystems.stock.domain.ModulosOrganizacion;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class ModulosOrganizacionService {

	@Autowired
	private ModulosOrganizacionDAO modulosOrganizacionDAO;

	public void save(final ModulosOrganizacion modulosOrganizacion) {
		modulosOrganizacionDAO.save(modulosOrganizacion);
	}

	public void delete(final ModulosOrganizacion modulosOrganizacion) {
		modulosOrganizacionDAO.delete(modulosOrganizacion);
	}

	public List<ModulosOrganizacion> getModulosByOrganizacion(
			final Organizacion organizacion) {
		return modulosOrganizacionDAO.getModulosByOrganizacion(organizacion);
	}
}
