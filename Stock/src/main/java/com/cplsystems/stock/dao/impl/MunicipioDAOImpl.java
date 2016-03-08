package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.MunicipioDAO;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MunicipioDAOImpl extends HibernateDAOSuportUtil implements MunicipioDAO {
	@Transactional
	public void save(Municipio municipio) {
		getHibernateTemplate().save(municipio);
	}

	@Transactional
	public void update(Municipio municipio) {
		getHibernateTemplate().update(municipio);
	}

	@Transactional
	public void delete(Municipio municipio) {
		getHibernateTemplate().delete(municipio);
	}

	@Transactional(readOnly = true)
	public Municipio getById(Long idMunicipio) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("idMunicipio", idMunicipio));
		List<Municipio> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Municipio) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Municipio> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Municipio.class);

		List<Municipio> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Municipio> getByEstado(Estado estado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("estado", estado));
		List<Municipio> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
