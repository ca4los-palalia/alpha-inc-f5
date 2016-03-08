package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProductoNaturalezaDAO;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductoNaturalezaDAOImpl extends HibernateDAOSuportUtil implements ProductoNaturalezaDAO {
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

	@Transactional(readOnly = true)
	public ProductoNaturaleza getById(Long idProductoNaturaleza) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		criteria.add(Restrictions.eq("idProductoNaturaleza", idProductoNaturaleza));
		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoNaturaleza) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ProductoNaturaleza> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public ProductoNaturaleza getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoNaturaleza) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public ProductoNaturaleza getBySimbolo(String simbolo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		criteria.add(Restrictions.eq("simbolo", simbolo));
		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoNaturaleza) lista.get(0) : null;
	}
}
