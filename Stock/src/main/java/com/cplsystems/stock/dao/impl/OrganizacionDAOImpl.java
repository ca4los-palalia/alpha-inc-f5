/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.OrganizacionDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class OrganizacionDAOImpl extends HibernateDAOSuportUtil implements
		OrganizacionDAO {

	@Transactional
	public void save(Organizacion organizacion) {
		getHibernateTemplate().saveOrUpdate(organizacion);

	}

	@Transactional
	public void delete(Organizacion organizacion) {
		getHibernateTemplate().delete(organizacion);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Organizacion> getOrganizaciones() {
		return getHibernateTemplate().find("FROM Organizacion as o");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Organizacion getOrganizacionByUsuario(Usuarios usuario) {
		List<Organizacion> organizaciones = getHibernateTemplate().find(
				"FROM Organizacion as o " + "WHERE o.usuarios = ? ", usuario);
		return organizaciones.size() > 0 ? organizaciones.get(0) : null;
	}

}
