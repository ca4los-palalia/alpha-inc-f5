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
import com.cplsystems.stock.dao.RequisicionDAO;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class RequisicionDAOImpl extends HibernateDAOSuportUtil implements
		RequisicionDAO {

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
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Requisicion getByPersona(Persona persona) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		criteria.add(Restrictions.eq("persona", persona));
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getUltimoFolio() {
		String folio = null;
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
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
		}
		return folio != null && !folio.isEmpty() ? folio : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Requisicion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Requisicion.class);
		List<Requisicion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista: null;
	}

}
