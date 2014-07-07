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
import com.cplsystems.stock.dao.GiroDAO;
import com.cplsystems.stock.domain.Giro;

/**
 * @author Carlos Palalía López
 */

@Repository
public class GiroDAOImpl extends HibernateDAOSuportUtil implements GiroDAO{

	@Transactional
	public void save(Giro giro) {
		getHibernateTemplate().saveOrUpdate(giro);
	}
	
	@Transactional
	public void delete(Giro giro) {
		getHibernateTemplate().delete(giro);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Giro getById(Long idGiro) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Giro.class);
		criteria.add(Restrictions.eq("idGiro", idGiro));
		List<Giro> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Giro> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Giro.class);
		List<Giro> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Giro getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Giro.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<Giro> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
}
