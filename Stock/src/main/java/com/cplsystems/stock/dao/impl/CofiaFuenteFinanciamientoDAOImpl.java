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
import com.cplsystems.stock.dao.CofiaFuenteFinanciamientoDAO;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class CofiaFuenteFinanciamientoDAOImpl extends HibernateDAOSuportUtil implements CofiaFuenteFinanciamientoDAO{

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		getHibernateTemplate().saveOrUpdate(cofiaFuenteFinanciamiento);
	}
	
	@Transactional
	public void delete(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		getHibernateTemplate().delete(cofiaFuenteFinanciamiento);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CofiaFuenteFinanciamiento getById(Long idCofiaFuenteFinanciamiento) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaFuenteFinanciamiento.class);
		criteria.add(Restrictions.eq("idCofiaFuenteFinanciamiento", idCofiaFuenteFinanciamiento));
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaFuenteFinanciamiento> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CofiaFuenteFinanciamiento> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaFuenteFinanciamiento.class);
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaFuenteFinanciamiento> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

}
