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
import com.cplsystems.stock.dao.ProductoTipoDAO;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProductoTipoDAOImpl extends HibernateDAOSuportUtil implements ProductoTipoDAO{

	@Transactional
	public void saveOrUpdate(ProductoTipo productoTipo) {
		getHibernateTemplate().saveOrUpdate(productoTipo);
	}
	
	@Transactional
	public void save(ProductoTipo productoTipo) {
		getHibernateTemplate().save(productoTipo);
	}
	
	@Transactional
	public void update(ProductoTipo productoTipo) {
		getHibernateTemplate().update(productoTipo);
	}

	@Transactional
	public void delete(ProductoTipo productoTipo) {
		getHibernateTemplate().delete(productoTipo);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoTipo getById(Long idProductoTipo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoTipo.class);
		criteria.add(Restrictions.eq("idProductoTipo", idProductoTipo));
		List<ProductoTipo> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProductoTipo> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoTipo.class);
		List<ProductoTipo> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoTipo getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(ProductoTipo.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<ProductoTipo> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
   
}
