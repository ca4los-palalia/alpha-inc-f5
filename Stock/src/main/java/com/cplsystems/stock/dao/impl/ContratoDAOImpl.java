/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.ContratoDAO;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ContratoDAOImpl extends HibernateDAOSuportUtil implements
		ContratoDAO {

	
	@Autowired
	private SessionUtils sessionUtils;
	
	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(Contrato contrato) {
		getHibernateTemplate().saveOrUpdate(contrato);
	}

	@Transactional
	public void delete(Contrato contrato) {
		getHibernateTemplate().delete(contrato);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contrato getById(Long idContrato) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contrato.class);
		criteria.add(Restrictions.eq("idContrato", idContrato));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Contrato> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Contrato> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contrato.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Contrato> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

}
