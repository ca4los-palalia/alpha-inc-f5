/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.OrdenCompraDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class OrdenCompraDAOImpl extends HibernateDAOSuportUtil implements OrdenCompraDAO{

	@Autowired
	private SessionUtils sessionUtils;
	
	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(OrdenCompra ordenCompra) {
		getHibernateTemplate().saveOrUpdate(ordenCompra);
	}
	
	@Transactional
	public void delete(OrdenCompra ordenCompra) {
		getHibernateTemplate().delete(ordenCompra);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public OrdenCompra getById(Long idOrdenCompra) {
		List<OrdenCompra> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(OrdenCompra.class);
		criteria.add(Restrictions.eq("idOrdenCompra", idOrdenCompra));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenCompra> getAll() {
		List<OrdenCompra> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(OrdenCompra.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion) {
		List<OrdenCompra> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(OrdenCompra.class);
		criteria.add(Restrictions.eq("cotizacion", cotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public OrdenCompra getByCodigo(String codigo) {
		List<OrdenCompra> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(OrdenCompra.class);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenCompra> getByFechaOrden(Calendar fechaOrden) {
		List<OrdenCompra> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(OrdenCompra.class);
		criteria.add(Restrictions.eq("fechaOrden", fechaOrden));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getCodigoDeOrden() {

		String folio = null;
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(OrdenCompra.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setProjection(Projections.rowCount());
		List<OrdenCompra> lista = criteria.list();
		
		if(lista != null){
			String numeroLista = String.valueOf(Integer.parseInt(String.valueOf(lista.get(0))) + 1);
			
				switch (numeroLista.length()) {
					case 1:
						folio = "00000" + numeroLista;
						break;
					case 2:
						folio = "0000" + numeroLista;
						break;
					case 3:
						folio = "000" + numeroLista;
						break;
					case 4:
						folio = "00" + numeroLista;
						break;
					case 5:
						folio = "0" + numeroLista;
						break;
				}
		}
		return folio != null && !folio.isEmpty() ? folio : null;
	
	}

}
