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
import com.cplsystems.stock.dao.EstadoDAO;
import com.cplsystems.stock.domain.Estado;

/**
 * @author Carlos Palalía López
 */

@Repository
public class EstadoDAOImpl extends HibernateDAOSuportUtil implements EstadoDAO{
	
	public void save(Estado estado) {
		getHibernateTemplate().save(estado);
	}

	public void update(Estado estado) {
		getHibernateTemplate().update(estado);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getById(Long estado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		criteria.add(Restrictions.eq("idEstado", estado));
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
		
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Estado> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	public void delete(Estado estado) {
		getHibernateTemplate().delete(estado);
	}

   
}
