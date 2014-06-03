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
import com.cplsystems.stock.dao.BancoDAO;
import com.cplsystems.stock.domain.Banco;

/**
 * @author Carlos Palalía López
 */

@Repository
public class BancoDAOImpl extends HibernateDAOSuportUtil implements BancoDAO{

	@Transactional
	public void saveOrUpdate(Banco banco) {
		getHibernateTemplate().saveOrUpdate(banco);
	}

	@Transactional
	public void save(Banco banco) {
		getHibernateTemplate().save(banco);
	}

	@Transactional
	public void update(Banco banco) {
		getHibernateTemplate().update(banco);
	}
	
	@Transactional
	public void delete(Banco banco) {
		getHibernateTemplate().delete(banco);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Banco getById(Long idBanco) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Banco.class);
		criteria.add(Restrictions.eq("idBanco", idBanco));
		criteria.setMaxResults(1);
		List<Banco> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Banco> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Banco.class);
		criteria.addOrder(Order.asc("nombre"));
		List<Banco> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	
}
