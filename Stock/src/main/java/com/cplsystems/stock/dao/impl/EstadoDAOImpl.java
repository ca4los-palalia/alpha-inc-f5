package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.EstadoDAO;
import com.cplsystems.stock.domain.Estado;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EstadoDAOImpl extends HibernateDAOSuportUtil implements EstadoDAO {
	@Transactional
	public void save(Estado estado) {
		getHibernateTemplate().save(estado);
	}

	@Transactional
	public void update(Estado estado) {
		getHibernateTemplate().update(estado);
	}

	@Transactional(readOnly = true)
	public Estado getById(Long estado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Estado.class);

		criteria.add(Restrictions.eq("idEstado", estado));
		List<Estado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Estado) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Estado> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Estado.class);

		List<Estado> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	public void delete(Estado estado) {
		getHibernateTemplate().delete(estado);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getByName(String name) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Estado.class);

		criteria.add(Restrictions.eq("nombre", name));
		List<Estado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Estado) lista.get(0) : null;
	}
}
