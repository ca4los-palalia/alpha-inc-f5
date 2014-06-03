/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CotizacionDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;



/**
 * @author Carlos Palalía López
 */

@Repository
public class CotizacionDAOImpl extends HibernateDAOSuportUtil  implements CotizacionDAO{

	public void save(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		
	}

	public void update(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByRequisicion(Requisicion requisicion) {
		// TODO Auto-generated method stub
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

}
