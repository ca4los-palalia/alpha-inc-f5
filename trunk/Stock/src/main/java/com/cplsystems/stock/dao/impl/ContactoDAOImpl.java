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
import com.cplsystems.stock.dao.ContactoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ContactoDAOImpl extends HibernateDAOSuportUtil  implements ContactoDAO{

	@Transactional
	public void save(Contacto contacto) {
		getHibernateTemplate().save(contacto);
	}

	@Transactional
	public void update(Contacto contacto) {
		getHibernateTemplate().update(contacto);
	}

	@Transactional
	public void delete(Contacto contacto) {
		getHibernateTemplate().delete(contacto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getById(Long idContacto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("", idContacto));
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getByTelefono(Telefono telefono) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("telefono", telefono));
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getByIdEmail(Email email) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("email", email));
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Contacto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contacto.class);
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getUltimoRegistroContacto() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Contacto.class);
		criteria.addOrder(Order.desc("idContacto"));
		criteria.setMaxResults(1);
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
   
}
