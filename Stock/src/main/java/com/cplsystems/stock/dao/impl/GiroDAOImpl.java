package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.GiroDAO;
import com.cplsystems.stock.domain.Giro;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GiroDAOImpl extends HibernateDAOSuportUtil implements GiroDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Giro giro) {
		getHibernateTemplate().saveOrUpdate(giro);
	}

	@Transactional
	public void delete(Giro giro) {
		getHibernateTemplate().delete(giro);
	}

	@Transactional(readOnly = true)
	public Giro getById(Long idGiro) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Giro.class);

		criteria.add(Restrictions.eq("idGiro", idGiro));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Giro> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Giro) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Giro> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Giro.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Giro> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Giro getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Giro.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Giro> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Giro) lista.get(0) : null;
	}
}
