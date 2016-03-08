package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ContactoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContactoDAOImpl extends HibernateDAOSuportUtil implements ContactoDAO {
	@Transactional
	public void save(Contacto contacto) {
		getHibernateTemplate().saveOrUpdate(contacto);
	}

	@Transactional
	public void update(Contacto contacto) {
		getHibernateTemplate().update(contacto);
	}

	@Transactional
	public void delete(Contacto contacto) {
		getHibernateTemplate().delete(contacto);
	}

	@Transactional(readOnly = true)
	public Contacto getById(Long idContacto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.add(Restrictions.eq("", idContacto));
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Contacto getByTelefono(Telefono telefono) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.add(Restrictions.eq("telefono", telefono));
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Contacto getByIdEmail(Email email) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.add(Restrictions.eq("email", email));
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Contacto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Contacto.class);

		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Contacto getUltimoRegistroContacto() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.addOrder(Order.desc("idContacto"));
		criteria.setMaxResults(1);
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Contacto getContactoByEmail(Email email) {
		List<Contacto> contactos = getHibernateTemplate()
				.find("FROM Contacto as c LEFT JOIN FETCH c.email as e WHERE c.email = ?", email);

		return contactos.size() > 0 ? (Contacto) contactos.get(0) : null;
	}
}
