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
import com.cplsystems.stock.dao.UnidadDAO;
import com.cplsystems.stock.domain.Unidad;

/**
 * @author Carlos Palalía López
 */

@Repository
public class UnidadDAOImpl extends HibernateDAOSuportUtil implements UnidadDAO{

	@Transactional
	public void save(Unidad unidad) {
		getHibernateTemplate().save(unidad);
	}

	@Transactional
	public void update(Unidad unidad) {
		getHibernateTemplate().update(unidad);
	}

	@Transactional
	public void delete(Unidad unidad) {
		getHibernateTemplate().delete(unidad);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Unidad getById(Long idUnidad) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Unidad.class);
		criteria.add(Restrictions.eq("idUnidad", idUnidad));
		List<Unidad> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Unidad> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Unidad.class);
		List<Unidad> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Unidad getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Unidad.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<Unidad> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
}
