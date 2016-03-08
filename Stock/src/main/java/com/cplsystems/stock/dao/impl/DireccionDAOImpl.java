package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.DireccionDAO;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
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
public class DireccionDAOImpl extends HibernateDAOSuportUtil implements DireccionDAO {
	@Transactional
	public void save(Direccion direccion) {
		getHibernateTemplate().save(direccion);
	}

	@Transactional
	public void update(Direccion direccion) {
		getHibernateTemplate().update(direccion);
	}

	@Transactional(readOnly = true)
	public Direccion getById(Long direccion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("idDireccion", direccion));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Direccion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByCodigoPostalId(String codigoPostal) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("cp", codigoPostal));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByEstado(Estado estado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("estado", estado));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByMunicipio(Municipio municipio) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("municipio", municipio));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Direccion.class);

		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Direccion getUltimoRegistroDireccion() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.addOrder(Order.desc("idDireccion"));
		criteria.setMaxResults(1);
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Direccion) lista.get(0) : null;
	}
}
