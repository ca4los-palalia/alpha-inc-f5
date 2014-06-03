/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.MonedaDAO;
import com.cplsystems.stock.domain.Moneda;

/**
 * @author Carlos Palalía López
 */

@Repository
public class MonedaDAOImpl extends HibernateDAOSuportUtil implements MonedaDAO{

	@Transactional
	public void saveOrUpdate(Moneda moneda) {
		getHibernateTemplate().saveOrUpdate(moneda);
	}
	
	@Transactional
	public void save(Moneda moneda) {
		getHibernateTemplate().save(moneda);
	}
	
	@Transactional
	public void update(Moneda moneda) {
		getHibernateTemplate().update(moneda);
	}

	@Transactional
	public void delete(Moneda moneda) {
		getHibernateTemplate().delete(moneda);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Moneda getById(Long idMoneda) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Moneda.class);
		criteria.add(Restrictions.eq("idMoneda", idMoneda));
		criteria.setMaxResults(1);
		List<Moneda> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Moneda> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Moneda.class);
		criteria.addOrder(Order.asc("nombre"));
		List<Moneda> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
}
