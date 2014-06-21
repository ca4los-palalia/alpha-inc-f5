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
import com.cplsystems.stock.dao.ProductoNaturalezaDAO;
import com.cplsystems.stock.domain.ProductoNaturaleza;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProductoNaturalezaDAOImpl extends HibernateDAOSuportUtil implements ProductoNaturalezaDAO{

	@Transactional
	public void save(ProductoNaturaleza productoNaturaleza) {
		getHibernateTemplate().save(productoNaturaleza);
	}

	@Transactional
	public void update(ProductoNaturaleza productoNaturaleza) {
		getHibernateTemplate().update(productoNaturaleza);
	}

	@Transactional
	public void delete(ProductoNaturaleza productoNaturaleza) {
		getHibernateTemplate().delete(productoNaturaleza);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoNaturaleza getById(Long idProductoNaturaleza) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoNaturaleza.class);
		criteria.add(Restrictions.eq("idProductoNaturaleza", idProductoNaturaleza));
		List<ProductoNaturaleza> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProductoNaturaleza> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoNaturaleza.class);
		List<ProductoNaturaleza> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoNaturaleza getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoNaturaleza.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<ProductoNaturaleza> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoNaturaleza getBySimbolo(String simbolo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoNaturaleza.class);
		criteria.add(Restrictions.eq("simbolo", simbolo));
		List<ProductoNaturaleza> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
}
