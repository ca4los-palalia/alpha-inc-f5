/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CotizacionDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;



/**
 * @author Carlos Palalía López
 */

@Repository
public class CotizacionDAOImpl extends HibernateDAOSuportUtil  implements CotizacionDAO{

	@Transactional
	public void save(Cotizacion cotizacion) {
		getHibernateTemplate().saveOrUpdate(cotizacion);
		
	}

	@Transactional
	public void update(Cotizacion cotizacion) {
		getHibernateTemplate().update(cotizacion);
	}

	@Transactional
	public void delete(Cotizacion cotizacion) {
		getHibernateTemplate().delete(cotizacion);
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Cotizacion getById(Long idCotizacion) {
		List<Cotizacion> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Cotizacion.class);
		criteria.add(Restrictions.eq("idCotizacion", idCotizacion));
		lista = criteria.list();
		
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cotizacion> getAll() {
		List<Cotizacion> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Cotizacion.class);
		lista = criteria.list();
		
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cotizacion> getByFechaEnvioCotizacion(
			Calendar fechaEnvioSolucion) {
		List<Cotizacion> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Cotizacion.class);
		criteria.add(Restrictions.eq("fechaResolucion", fechaEnvioSolucion));
		lista = criteria.list();
		
		return lista.size() > 0 ? lista : null;
	}

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion) {
		return null;
	}

	public List<Cotizacion> getByStatus(Integer status) {
		return null;
	}

	public List<Cotizacion> getByProveedor(Proveedor proveedor) {
		return null;
	}

	public List<Cotizacion> getByRequisicion(Requisicion requisicion) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cotizacion> getTopCompras() {
		List<Cotizacion> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Cotizacion.class);
		criteria.addOrder(Order.desc("idCotizacion"));
		criteria.setMaxResults(50);
		lista = criteria.list();
		
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Long getCountRowsCotizacion() {
		Long count = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Cotizacion.class);
		criteria.setProjection(Projections.rowCount());
		count = (Long) criteria.list().get(0);
		count = count + 1;
		
		return count > 0 ? count : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Cotizacion getCotizacionByFolio(String folioCotizacion) {
		List<Cotizacion> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Cotizacion.class);
		criteria.add(Restrictions.eq("folioCotizacion", folioCotizacion));
		lista = criteria.list();
		
		return lista.size() > 0 ? lista.get(0) : null;
	}

}
