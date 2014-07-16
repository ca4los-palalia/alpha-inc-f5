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
import com.cplsystems.stock.dao.PosicionDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Posicion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class PosicionDAOImpl extends HibernateDAOSuportUtil implements PosicionDAO{

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void saveOrUpdate(Posicion posicion) {
		getHibernateTemplate().saveOrUpdate(posicion);
	}
	@Transactional
	public void update(Posicion posicion) {
		getHibernateTemplate().update(posicion);
	}
	@Transactional
	public void save(Posicion posicion) {
		getHibernateTemplate().save(posicion);
	}

	@Transactional
	public void delete(Posicion posicion) {
		getHibernateTemplate().delete(posicion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Posicion getById(Long idPosicion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Posicion.class);
		criteria.add(Restrictions.eq("idPosicion", idPosicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Posicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Posicion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Posicion.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Posicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}	
	
}
