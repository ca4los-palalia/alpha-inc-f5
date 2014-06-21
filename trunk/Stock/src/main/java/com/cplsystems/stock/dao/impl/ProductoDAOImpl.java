/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public Producto getById(Long idProducto) {
		List<Producto> producto = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Producto.class);
		criteria.setFetchMode("unidad", FetchMode.JOIN);
		criteria.add(Restrictions.eq("idProducto", idProducto));
		producto = criteria.list();
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

		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		criteria.setFetchMode("unidad", FetchMode.JOIN);
		criteria.add(Restrictions.sqlRestriction("clave LIKE '%" + buscarTexto
				+ "%'"));
		criteria.addOrder(Order.asc("idProducto"));

		lista = criteria.list();

		if (lista.equals(null) || lista.size() < 1) {
			Criteria criteria2 = getHibernateTemplate().getSessionFactory()
					.openSession().createCriteria(Producto.class);
			criteria.setFetchMode("unidad", FetchMode.JOIN);
			criteria2.add(Restrictions.sqlRestriction("nombre LIKE '%"
					+ buscarTexto + "%'"));
			criteria2.addOrder(Order.asc("idProducto"));
			lista = criteria2.list();
		}
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getByTipo(ProductoTipo productoTipo) {

		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("productoTipo", productoTipo));
		List<Producto> tipo = criteria.list();
		return tipo.size() > 0 ? tipo : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getPreciosMaximos() {
		List<Producto> lista = null;

		Criteria crMax = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		crMax.setProjection(Projections.max("precio"));
		Float maximo = (Float) crMax.list().get(0);

		Criteria crAvg = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		crAvg.setProjection(Projections.avg("precio"));
		List<Double> listAvg = crAvg.list();
		Float promedio = recuperarPromedio(listAvg);

		if (promedio != null && promedio > 0F) {
			if (maximo != null && maximo > 0F) {
				Criteria criteria = getHibernateTemplate().getSessionFactory()
						.openSession().createCriteria(Producto.class);
				criteria.add(Restrictions.between("precio", promedio, maximo));
				criteria.setMaxResults(1000);
				lista = criteria.list();
			}
		}
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getPreciosMinimos() {
		List<Producto> lista = null;

		Criteria crMin = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		crMin.setProjection(Projections.min("precio"));
		Float minimo = (Float) crMin.list().get(0);

		Criteria crAvg = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		crAvg.setProjection(Projections.avg("precio"));
		List<Double> listAvg = crAvg.list();
		Float promedio = recuperarPromedio(listAvg);

		if (promedio != null && promedio > 0F) {
			if (minimo != null && minimo > 0F) {
				Criteria criteria = getHibernateTemplate().getSessionFactory()
						.openSession().createCriteria(Producto.class);
				criteria.add(Restrictions.between("precio", minimo, promedio));
				criteria.setMaxResults(1000);
				lista = criteria.list();
			}
		}
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getPreciosPromedio() {
		List<Producto> lista = null;

		Criteria crAvg = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		crAvg.setProjection(Projections.avg("precio"));
		List<Double> listAvg = crAvg.list();
		Float promedio = recuperarPromedio(listAvg);

		if (promedio != null && promedio > 0F) {
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.openSession().createCriteria(Producto.class);
			criteria.add(Restrictions.eq("precio", promedio));
			criteria.setMaxResults(1000);
			lista = criteria.list();
		}
		return lista.size() > 0 ? lista : null;
	}

	private Float recuperarPromedio(List<Double> listAvg) {
		Float resultado = 0F;
		if (listAvg != null && listAvg.size() > 0) {
			DecimalFormat df = new DecimalFormat("###.###");
			String convercion = df.format(listAvg.get(0));
			String[] floatComoArray = convercion.split(",");
			resultado = Float.parseFloat(floatComoArray[0] + "."
					+ floatComoArray[1]);
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getByPrecio(String precio) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.sqlRestriction(" precio LIKE '" + precio
				+ "%'"));
		List<Producto> tipo = criteria.list();
		return tipo.size() > 0 ? tipo : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Producto getByClaveNombrePrecioCosto(String buscarTexto) {
		List<Producto> lista = null;

		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Producto.class);
		criteria.setFetchMode("unidad", FetchMode.JOIN);
		criteria.add(Restrictions.sqlRestriction("clave LIKE '" + buscarTexto
				+ "'"));
		criteria.addOrder(Order.asc("idProducto"));
		criteria.setMaxResults(1);

		lista = criteria.list();

		if (lista.equals(null) || lista.size() < 1) {
			Criteria criteria2 = getHibernateTemplate().getSessionFactory()
					.openSession().createCriteria(Producto.class);
			criteria.setFetchMode("unidad", FetchMode.JOIN);
			criteria2.add(Restrictions.sqlRestriction("nombre LIKE '"
					+ buscarTexto + "'"));
			criteria2.addOrder(Order.asc("idProducto"));
			criteria2.setMaxResults(1);
			lista = criteria2.list();
		}
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
}
