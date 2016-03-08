package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.CofiaPyDAO;
import com.cplsystems.stock.domain.CofiaPy;
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
public class CofiaPyDAOImpl extends HibernateDAOSuportUtil implements CofiaPyDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(CofiaPy cofiaPy) {
		getHibernateTemplate().saveOrUpdate(cofiaPy);
	}

	@Transactional
	public void delete(CofiaPy cofiaPy) {
		getHibernateTemplate().delete(cofiaPy);
	}

	@Transactional(readOnly = true)
	public CofiaPy getById(Long idCofiaPy) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CofiaPy.class);

		criteria.add(Restrictions.eq("idCofiaPy", idCofiaPy));

		List<CofiaPy> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (CofiaPy) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CofiaPy> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CofiaPy.class);

		List<CofiaPy> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
