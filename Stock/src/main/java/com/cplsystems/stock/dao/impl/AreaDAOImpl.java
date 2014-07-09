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
import com.cplsystems.stock.dao.AreaDAO;
import com.cplsystems.stock.domain.Area;

/**
 * @author Carlos Palalía López
 */

@Repository
public class AreaDAOImpl extends HibernateDAOSuportUtil implements AreaDAO{
	
	@Transactional
	public void save(Area area) {
		getHibernateTemplate().save(area);
	}

	@Transactional
	public void update(Area area) {
		getHibernateTemplate().update(area);
	}
	

	@Transactional
	public void delete(Area posicion) {
		getHibernateTemplate().delete(posicion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Area getById(Long idArea) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Area.class);
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.setMaxResults(1);
		List<Area> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Area> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Area.class);
		List<Area> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Area getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Area.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.setMaxResults(1);
		List<Area> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}	
}
