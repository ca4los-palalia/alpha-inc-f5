package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.OrganizacionDAO;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrganizacionDAOImpl extends HibernateDAOSuportUtil implements OrganizacionDAO {
	@Transactional
	public void save(Organizacion organizacion) {
		getHibernateTemplate().saveOrUpdate(organizacion);
	}

	@Transactional
	public void delete(Organizacion organizacion) {
		getHibernateTemplate().delete(organizacion);
	}

	@Transactional(readOnly = true)
	public List<Organizacion> getOrganizaciones() {
		return getHibernateTemplate().find("FROM Organizacion as o");
	}

	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByNombreRFC(String compania, String rfc) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.like("nombre", "%" + compania + "%"));
		criteria.add(Restrictions.like("rfc", "%" + rfc + "%"));
		return criteria.list();
	}

	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByNombre(String compania) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.like("nombre", "%" + compania + "%"));
		return criteria.list();
	}

	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByRFC(String rfc) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.like("rfc", "%" + rfc + "%"));
		return criteria.list();
	}

	@Transactional(readOnly = true)
	public List<Organizacion> getAll() {
		return getHibernateTemplate().find("FROM Organizacion as o");
	}

	@Transactional(readOnly = true)
	public Organizacion getById(Long idOrganizacion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.eq("idOrganizacion", idOrganizacion));
		List<Organizacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Organizacion) lista.get(0) : null;
	}
}
