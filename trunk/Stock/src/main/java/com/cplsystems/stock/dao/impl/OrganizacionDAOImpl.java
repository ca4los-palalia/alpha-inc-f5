/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.OrganizacionDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Requisicion;

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
	public List<Organizacion> getCompaniasByNombreRFC(String compania,
			String rfc) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.like("nombre", "%" + compania + "%"));
		criteria.add(Restrictions.like("rfc", "%" + rfc + "%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByNombre(String compania) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.like("nombre", "%" + compania + "%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByRFC(String rfc) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.like("rfc", "%" + rfc + "%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)	
	public List<Organizacion> getAll() {
		return getHibernateTemplate().find("FROM Organizacion as o");
	}

	@Override
	public Organizacion getById(Long idOrganizacion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Organizacion.class);
		criteria.add(Restrictions.eq("idOrganizacion", idOrganizacion));
		List<Organizacion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

}
