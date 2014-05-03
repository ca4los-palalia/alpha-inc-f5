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
import com.cplsystems.stock.dao.ContratoDAO;
import com.cplsystems.stock.domain.Contrato;


/**
 * @author Carlos Palalía López
 */

@Repository
public class ContratoDAOImpl extends HibernateDAOSuportUtil implements ContratoDAO{

	public void save(Contrato contrato) {
		getHibernateTemplate().save(contrato);
	}

	public void update(Contrato contrato) {
		getHibernateTemplate().update(contrato);
	}

	public void delete(Contrato contrato) {
		getHibernateTemplate().delete(contrato);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contrato getById(Long idContrato) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contrato.class);
		criteria.add(Restrictions.eq("idContrato", idContrato));
		List<Contrato> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Contrato> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contrato.class);
		List<Contrato> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	
}
