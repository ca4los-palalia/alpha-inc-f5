package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.PaisDAO;
import com.cplsystems.stock.domain.Pais;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PaisDAOImpl extends HibernateDAOSuportUtil implements PaisDAO {
	@Transactional
	public void save(Pais pais) {
		getHibernateTemplate().save(pais);
	}

	@Transactional
	public void delete(Pais pais) {
		getHibernateTemplate().delete(pais);
	}

	@Transactional(readOnly = true)
	public List<Pais> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Pais.class);

		List<Pais> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Pais findById(Long idPais) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Pais.class);

		criteria.add(Restrictions.eq("idPais", idPais));
		List<Pais> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Pais) lista.get(0) : null;
	}
}
