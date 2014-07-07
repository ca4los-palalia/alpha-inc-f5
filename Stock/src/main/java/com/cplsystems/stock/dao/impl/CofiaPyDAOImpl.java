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
import com.cplsystems.stock.dao.CofiaPyDAO;
import com.cplsystems.stock.domain.CofiaPy;

/**
 * @author Carlos Palalía López
 */

@Repository
public class CofiaPyDAOImpl extends HibernateDAOSuportUtil implements CofiaPyDAO{

	@Transactional
	public void save(CofiaPy cofiaPy) {
		getHibernateTemplate().saveOrUpdate(cofiaPy);
	}
	
	@Transactional
	public void delete(CofiaPy cofiaPy) {
		getHibernateTemplate().delete(cofiaPy);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CofiaPy getById(Long idCofiaPy) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPy.class);
		criteria.add(Restrictions.eq("idCofiaPy", idCofiaPy));
		List<CofiaPy> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CofiaPy> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPy.class);
		List<CofiaPy> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

}
