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
import com.cplsystems.stock.dao.CofiaProgDAO;
import com.cplsystems.stock.domain.CofiaProg;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class CofiaProgDAOImpl extends HibernateDAOSuportUtil implements CofiaProgDAO{

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(CofiaProg cofiaProg) {
		getHibernateTemplate().saveOrUpdate(cofiaProg);
	}
	
	@Transactional
	public void delete(CofiaProg cofiaProg) {
		getHibernateTemplate().delete(cofiaProg);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CofiaProg getById(Long idCofiaProg) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaProg.class);
		criteria.add(Restrictions.eq("idCofiaProg", idCofiaProg));
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaProg> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CofiaProg> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaProg.class);
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaProg> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

}
