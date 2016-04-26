package com.cplsystems.stock.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.ReconstruccionNativeSQL;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;

@Repository
public class ProductoDAOImpl extends HibernateDAOSuportUtil implements ProductoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Producto producto) {
		getHibernateTemplate().saveOrUpdate(producto);
	}

	@Transactional
	public void delete(Producto producto) {
		getHibernateTemplate().delete(producto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Producto getById(Long idProducto) {
		List<Producto> producto = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.setFetchMode("unidad", FetchMode.JOIN);
		criteria.add(Restrictions.eq("idProducto", idProducto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		producto = criteria.list();
		return producto.size() > 0 ? (Producto) producto.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getAll() {
		List<Producto> producto = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		producto = criteria.list();
		
		return producto.size() > 0 ? producto : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getItemByKeyOrName(String claveProducto, String nombreProducto) {
		boolean restriccion1 = false;
		boolean restriccion2 = false;
		List<Producto> productos = new ArrayList<>();

		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);
		if ((claveProducto != null) && (!claveProducto.isEmpty())) {
			criteria.add(Restrictions.like("clave", "%" + claveProducto + "%"));
			restriccion1 = true;
		}
		if ((nombreProducto != null) && (!nombreProducto.isEmpty())) {
			criteria.add(Restrictions.like("nombre", "%" + nombreProducto + "%"));
			restriccion2 = true;
		}

		if (restriccion1 || restriccion2) {
			criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
			productos = criteria.list();
		}
		return productos.size() > 0 ? productos : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<String> getAllKeys() {
		return getHibernateTemplate().find("SELECT clave FROM Producto as p ");
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getByClaveNombre(String buscarTexto) {
		List<Producto> lista = null;

		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.add(Restrictions.ilike("clave", "%" + buscarTexto + "%"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.asc("idProducto"));

		lista = criteria.list();
		if ((lista.equals(null)) || (lista.size() < 1)) {
			Criteria criteria2 = getHibernateTemplate().getSessionFactory().openSession()
					.createCriteria(Producto.class);

			criteria2.add(Restrictions.ilike("nombre", "%" + buscarTexto + "%"));
			criteria2.add(Restrictions.eq("organizacion", getOrganizacion()));
			criteria2.addOrder(Order.asc("idProducto"));
			lista = criteria2.list();
		}
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getPreciosMaximos() {
		List<Producto> lista = null;

		Criteria crMax = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		crMax.setProjection(Projections.max("precio"));
		Float maximo = (Float) crMax.list().get(0);

		Criteria crAvg = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		crAvg.setProjection(Projections.avg("precio"));
		List<Double> listAvg = crAvg.list();
		Float promedio = recuperarPromedio(listAvg);
		if ((promedio != null) && (promedio.floatValue() > 0.0F) && (maximo != null) && (maximo.floatValue() > 0.0F)) {
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

			criteria.add(Restrictions.between("precio", promedio, maximo));
			criteria.setMaxResults(1000);
			lista = criteria.list();
		}
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getPreciosMinimos() {
		List<Producto> lista = null;

		Criteria crMin = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		crMin.setProjection(Projections.min("precio"));
		Float minimo = (Float) crMin.list().get(0);

		Criteria crAvg = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		crAvg.setProjection(Projections.avg("precio"));
		List<Double> listAvg = crAvg.list();
		Float promedio = recuperarPromedio(listAvg);
		if ((promedio != null) && (promedio.floatValue() > 0.0F) && (minimo != null) && (minimo.floatValue() > 0.0F)) {
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

			criteria.add(Restrictions.between("precio", minimo, promedio));
			criteria.setMaxResults(1000);
			lista = criteria.list();
		}
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getPreciosPromedio() {
		List<Producto> lista = null;

		Criteria crAvg = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		crAvg.setProjection(Projections.avg("precio"));
		List<Double> listAvg = crAvg.list();
		Float promedio = recuperarPromedio(listAvg);
		if ((promedio != null) && (promedio.floatValue() > 0.0F)) {
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

			criteria.add(Restrictions.eq("precio", promedio));
			criteria.setMaxResults(1000);
			lista = criteria.list();
		}
		return lista.size() > 0 ? lista : null;
	}

	private Float recuperarPromedio(List<Double> listAvg) {
		Float resultado = Float.valueOf(0.0F);
		if ((listAvg != null) && (listAvg.size() > 0)) {
			DecimalFormat df = new DecimalFormat("###.###");
			String convercion = df.format(listAvg.get(0));
			String[] floatComoArray = convercion.split(",");
			resultado = Float.valueOf(Float.parseFloat(floatComoArray[0] + "." + floatComoArray[1]));
		}
		return resultado;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getByPrecio(String precio) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.sqlRestriction(" precio LIKE '" + precio + "%'"));

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Producto> tipo = criteria.list();
		return tipo.size() > 0 ? tipo : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Producto getByClaveNombrePrecioCosto(String buscarTexto) {
		List<Producto> lista = null;

		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.setFetchMode("unidad", FetchMode.JOIN);

		criteria.add(Restrictions.ilike("clave", "%" + buscarTexto + "%"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.asc("idProducto"));
		criteria.setMaxResults(1);

		lista = criteria.list();
		if ((lista.equals(null)) || (lista.size() < 1)) {
			Criteria criteria2 = getHibernateTemplate().getSessionFactory().openSession()
					.createCriteria(Producto.class);

			criteria2.setFetchMode("unidad", FetchMode.JOIN);
			criteria2.add(Restrictions.ilike("nombre", "%" + buscarTexto + "%"));
			criteria2.add(Restrictions.eq("organizacion", getOrganizacion()));
			criteria2.addOrder(Order.asc("idProducto"));
			criteria2.setMaxResults(1);
			lista = criteria2.list();
		}
		return (lista != null) && (!lista.isEmpty()) ? (Producto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Producto getByClave(String clave) {
		List<Producto> lista = null;

		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.add(Restrictions.eq("clave", clave));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);

		lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Producto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> getAllLimited() {
		List<Producto> producto = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Producto.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.desc("idProducto"));
		criteria.setMaxResults(250);
		producto = criteria.list();
		return producto.size() > 0 ? producto : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Producto> getAllNativeSQL() {
		List<Producto> lista = new ArrayList<Producto>();
		ReconstruccionNativeSQL reconstruccion = new ReconstruccionNativeSQL();

		SessionFactory sessionFactory = getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		Transaction tx = session.beginTransaction();

		SQLQuery query = session.createSQLQuery("SELECT * FROM Producto");
		List<Object[]> allProductos = query.list();

		for (Object[] producto : allProductos) {
			Producto p = reconstruccion.getProducto(producto);
			lista.add(p);
		}
		
		return lista.size() > 0 ? lista : null;
	}
}
