package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.EmailDAO;
import com.cplsystems.stock.domain.Email;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmailDAOImpl extends HibernateDAOSuportUtil implements EmailDAO {
	@Transactional
	public void save(Email email) {
		getHibernateTemplate().saveOrUpdate(email);
	}

	@Transactional
	public void update(Email email) {
		getHibernateTemplate().saveOrUpdate(email);
	}

	@Transactional
	public void delete(Email email) {
		getHibernateTemplate().delete(email);
	}

	@Transactional(readOnly = true)
	public Email getById(Long idEmail) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Email.class);

		criteria.add(Restrictions.eq("idEmail", idEmail));
		List<Email> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Email) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Email> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Email.class);

		List<Email> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Email getUltimoRegistroEmail() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Email.class);

		criteria.addOrder(Order.desc("idEmails"));
		criteria.setMaxResults(1);
		List<Email> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Email) lista.get(0) : null;
	}
}
