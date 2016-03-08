package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProveedorProductoDAO;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProveedorProductoDAOImpl extends HibernateDAOSuportUtil implements ProveedorProductoDAO {
	@Transactional
	public void save(ProveedorProducto proveedorProducto) {
		getHibernateTemplate().save(proveedorProducto);
	}

	@Transactional
	public void update(ProveedorProducto proveedorProducto) {
		getHibernateTemplate().update(proveedorProducto);
	}

	@Transactional
	public void delete(ProveedorProducto proveedorProducto) {
		getHibernateTemplate().delete(proveedorProducto);
	}

	@Transactional(readOnly = true)
	public ProveedorProducto getById(Long idProveedorProducto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("idProveedorProdcuto", idProveedorProducto));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProveedorProducto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getByProveedor(Proveedor Proveedor) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("proveedor", Proveedor));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getByProducto(Producto producto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.addOrder(Order.desc("idProveedorProdcuto"));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getByProductoProveedor(Producto producto, Proveedor proveedor) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("producto", producto));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
