package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ModulosOrganizacionDAO;
import com.cplsystems.stock.domain.ModulosOrganizacion;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ModulosOrganizacionDAOImpl extends HibernateDAOSuportUtil implements ModulosOrganizacionDAO {
	@Transactional
	public void delete(ModulosOrganizacion modulosOrganizacion) {
		getHibernateTemplate().delete(modulosOrganizacion);
	}

	@Transactional(readOnly = true)
	public List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion organizacion) {
		return getHibernateTemplate().find("FROM ModulosOrganizacion as m");
	}

	@Transactional
	public void save(ModulosOrganizacion modulosOrganizacion) {
		getHibernateTemplate().save(modulosOrganizacion);
	}
}
