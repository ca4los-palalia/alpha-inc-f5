/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ModulosOrganizacionDAO;
import com.cplsystems.stock.domain.ModulosOrganizacion;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class ModulosOrganizacionDAOImpl extends HibernateDAOSuportUtil
		implements ModulosOrganizacionDAO {

	@Transactional
	public void delete(ModulosOrganizacion modulosOrganizacion) {
		getHibernateTemplate().delete(modulosOrganizacion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ModulosOrganizacion> getModulosByOrganizacion(
			Organizacion organizacion) {
		return getHibernateTemplate().find("FROM ModulosOrganizacion as m");
	}

	@Transactional
	public void save(ModulosOrganizacion modulosOrganizacion) {
		getHibernateTemplate().save(modulosOrganizacion);
	}

}
