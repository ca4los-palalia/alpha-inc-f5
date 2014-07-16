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
import com.cplsystems.stock.dao.CofiaPartidaGenericaDAO;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class CofiaPartidaGenericaDAOImpl extends HibernateDAOSuportUtil implements CofiaPartidaGenericaDAO{

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(CofiaPartidaGenerica cofiaPartidaGenerica) {
		getHibernateTemplate().saveOrUpdate(cofiaPartidaGenerica);
	}
	
	@Transactional
	public void delete(CofiaPartidaGenerica cofiaPartidaGenerica) {
		getHibernateTemplate().delete(cofiaPartidaGenerica);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CofiaPartidaGenerica getById(Long idCofiaPartidaGenerica) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPartidaGenerica.class);
		criteria.add(Restrictions.eq("idCofiaPartidaGenerica", idCofiaPartidaGenerica));
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaPartidaGenerica> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CofiaPartidaGenerica> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPartidaGenerica.class);
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CofiaPartidaGenerica> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CofiaPartidaGenerica getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CofiaPartidaGenerica.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<CofiaPartidaGenerica> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

}
