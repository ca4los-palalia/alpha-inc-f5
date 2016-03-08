package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.TelefonoDAO;
import com.cplsystems.stock.domain.Telefono;
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
public class TelefonoDAOImpl extends HibernateDAOSuportUtil implements TelefonoDAO {
	@Transactional
	public void save(Telefono telefono) {
		getHibernateTemplate().saveOrUpdate(telefono);
	}

	@Transactional
	public void update(Telefono telefono) {
		getHibernateTemplate().update(telefono);
	}

	@Transactional
	public void delete(Telefono telefono) {
		getHibernateTemplate().delete(telefono);
	}

	@Transactional(readOnly = true)
	public Telefono getById(Long idTelefono) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Telefono.class);

		criteria.add(Restrictions.eq("idTelefono", idTelefono));
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Telefono) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Telefono> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Telefono.class);

		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Telefono getUltimoregistroEmail() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Telefono.class);

		criteria.addOrder(Order.desc("idTelefono"));
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Telefono) lista.get(0) : null;
	}
}
