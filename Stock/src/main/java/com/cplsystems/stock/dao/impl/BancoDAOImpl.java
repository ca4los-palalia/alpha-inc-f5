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
import com.cplsystems.stock.dao.BancoDAO;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class BancoDAOImpl extends HibernateDAOSuportUtil implements BancoDAO{

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(Banco banco) {
		getHibernateTemplate().saveOrUpdate(banco);
	}
	
	@Transactional
	public void delete(Banco banco) {
		getHibernateTemplate().delete(banco);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Banco getById(Long idBanco) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Banco.class);
		criteria.add(Restrictions.eq("idBanco", idBanco));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Banco> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Banco> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Banco.class);
		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Banco> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	
}
