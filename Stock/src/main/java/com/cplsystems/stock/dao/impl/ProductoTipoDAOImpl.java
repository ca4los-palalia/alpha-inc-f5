package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.ProductoTipoDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductoTipoDAOImpl extends HibernateDAOSuportUtil implements ProductoTipoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

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

	@Transactional(readOnly = true)
	public ProductoTipo getById(Long idProductoTipo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(ProductoTipo.class);

		criteria.add(Restrictions.eq("idProductoTipo", idProductoTipo));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ProductoTipo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoTipo) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ProductoTipo> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(ProductoTipo.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ProductoTipo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public ProductoTipo getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(ProductoTipo.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ProductoTipo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoTipo) lista.get(0) : null;
	}
}
