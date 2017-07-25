package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.CofiaProgDAO;
import com.cplsystems.stock.domain.CofiaProg;
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
public class CofiaProgDAOImpl extends HibernateDAOSuportUtil implements CofiaProgDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(CofiaProg cofiaProg) {
		getHibernateTemplate().saveOrUpdate(cofiaProg);
	}

	@Transactional
	public void delete(CofiaProg cofiaProg) {
		getHibernateTemplate().delete(cofiaProg);
	}

	@Transactional(readOnly = true)
	public CofiaProg getById(Long idCofiaProg) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CofiaProg.class);

		criteria.add(Restrictions.eq("idCofiaProg", idCofiaProg));

		List<CofiaProg> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (CofiaProg) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CofiaProg> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CofiaProg.class);

		List<CofiaProg> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
