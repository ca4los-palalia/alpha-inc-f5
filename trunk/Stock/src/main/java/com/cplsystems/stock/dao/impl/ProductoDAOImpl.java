/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;

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

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<String> getAllKeys() {
		return getHibernateTemplate().find("SELECT clave FROM Producto as p ");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getByClaveNombre(String buscarTexto) {
		List<Producto> lista = null;
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Producto.class);
		criteria.add(Restrictions.sqlRestriction("clave LIKE '%" + buscarTexto + "%'"));
		criteria.addOrder(Order.desc("idProducto"));
		lista = criteria.list();
		
		if(lista.equals(null) || lista.size() < 1){
			Criteria criteria2 = getHibernateTemplate().getSessionFactory().openSession().
					createCriteria(Producto.class);
			criteria2.add(Restrictions.sqlRestriction("nombre LIKE '%" + buscarTexto + "%'"));
			criteria2.addOrder(Order.desc("idProducto"));
			lista = criteria2.list();
		}
		
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getByTipo(ProductoTipo productoTipo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		
		criteria.add(Restrictions.eq("productoTipo",productoTipo));
		
		List<Producto> tipo = criteria.list();
		return tipo.size() > 0 ? tipo : null;
	}
}
