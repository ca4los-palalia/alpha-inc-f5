/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.CofiaPyDAO;
import com.cplsystems.stock.domain.CofiaPy;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class CofiaPyDAOImpl extends HibernateDAOSuportUtil implements CofiaPyDAO{

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(CofiaPy cofiaPy) {
		getHibernateTemplate().saveOrUpdate(cofiaPy);
	}
	
	@Transactional
	public void delete(CofiaPy cofiaPy) {
		getHibernateTemplate().delete(cofiaPy);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CofiaPy getById(Long idCofiaPy) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPy.class);
		criteria.add(Restrictions.eq("idCofiaPy", idCofiaPy));
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaPy> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CofiaPy> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPy.class);
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaPy> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

}
