/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.dao.RequisicionDAO;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.services.EstatusRequisicionService;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class RequisicionDAOImpl extends HibernateDAOSuportUtil implements
		RequisicionDAO {
	@Autowired
	EstatusRequisicionService estatusRequisicionService;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(Requisicion requisicion) {
		getHibernateTemplate().saveOrUpdate(requisicion);
	}

	@Transactional
	public void update(Requisicion requisicion) {
		getHibernateTemplate().update(requisicion);
	}
	
	@Transactional
	public void delete(Requisicion requisicion) {
		getHibernateTemplate().delete(requisicion);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Requisicion getById(Long idRequisicion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		criteria.add(Restrictions.eq("idRequisicion", idRequisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Requisicion getByPersona(Persona persona) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		criteria.add(Restrictions.eq("persona", persona));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getUltimoFolio() {
		String folio = null;
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.desc("folio"));
		criteria.setMaxResults(1);
		List<Requisicion> lista = criteria.list();
		
		if(lista != null){
			if(lista.size() == 0)
				folio = "1";
			else{
				String numeroLista = lista.get(0).getFolio();
				String numero = "";
				for (int i = 0; i < numeroLista.length(); i++) {
					
					try {
						Integer.parseInt(numeroLista.substring(i, (i+1)));
						numero = numeroLista.substring(i, (i+1));
					} catch (Exception e) {}
					
				}
				folio = String.valueOf(Integer.parseInt(numero) + 1);
			}
			
			if(folio.length() > 0){
				switch (folio.length()) {
					case 1:
						folio = "00000" + folio;
						break;
					case 2:
						folio = "0000" + folio;
						break;
						
					case 3:
						folio = "000" + folio;
						break;
					case 4:
						folio = "00" + folio;
						break;
					case 5:
						folio = "0" + folio;
						break;
				}
			}
		}
		return folio != null && !folio.isEmpty() ? folio : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Requisicion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista: null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Requisicion> getByEstatusRequisicion(
			EstatusRequisicion estatusRequisicion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		criteria.add(Restrictions.eq("estatusRequisicion", estatusRequisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista: null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Requisicion getByFolio(String folio) {
		List<Requisicion> lista = null;
		
		EstatusRequisicion estatus = estatusRequisicionService.getByClave(StockConstants.ESTADO_REQUISICION_NUEVA);
		if(estatus != null){
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
					createCriteria(Requisicion.class);
			criteria.add(Restrictions.ilike("folio", "" + folio + "%"));
			criteria.add(Restrictions.eq("estatusRequisicion", estatus));
			criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
			criteria.setMaxResults(1);
			lista = criteria.list();
		}
		
		
		return lista != null && !lista.isEmpty() ? lista.get(0): null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Requisicion> getByUnidadResponsable(Area area) {
		List<Requisicion> lista = null;
		
		EstatusRequisicion estatus = estatusRequisicionService.getByClave(StockConstants.ESTADO_REQUISICION_NUEVA);
		if(estatus != null){
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
					createCriteria(Requisicion.class);
			criteria.add(Restrictions.eq("area", area));
			criteria.add(Restrictions.eq("estatusRequisicion", estatus));
			criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
			criteria.setMaxResults(1);
			lista = criteria.list();
		}
		
		return lista != null && !lista.isEmpty() ? lista: null;
	}

}
