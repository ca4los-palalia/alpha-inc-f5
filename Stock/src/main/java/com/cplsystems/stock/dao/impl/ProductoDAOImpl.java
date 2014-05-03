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
import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProductoDAOImpl extends HibernateDAOSuportUtil implements
		ProductoDAO {

	@Transactional
	public void save(Producto producto) {
		getHibernateTemplate().saveOrUpdate(producto);

	}

	@Transactional
	public void delete(Producto producto) {
		getHibernateTemplate().delete(producto);

	}

	@SuppressWarnings("unchecked")
	public Producto getById(Long idProducto) {
		List<Producto> producto = getHibernateTemplate().find(
				"FROM Producto as p WHERE p.idProducto = ?", idProducto);
		return producto.size() > 0 ? producto.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getAll() {
		return getHibernateTemplate().find("FROM Producto as p");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getItemByKeyOrName(String claveProducto,
			String nombreProducto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		if (claveProducto != null && !claveProducto.isEmpty()) {
			criteria.add(Restrictions.like("clave", "%" + claveProducto + "%"));
		}
		if (nombreProducto != null && !nombreProducto.isEmpty()) {
			criteria.add(Restrictions
					.like("nombre", "%" + nombreProducto + "%"));
		}
		List<Producto> productos = criteria.list();
		return productos.size() > 0 ? productos : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<String> getAllKeys() {
		return getHibernateTemplate().find("SELECT clave FROM Producto as p ");
	}

}
