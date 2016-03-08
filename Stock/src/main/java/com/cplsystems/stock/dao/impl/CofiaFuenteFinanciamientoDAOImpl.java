package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.CofiaFuenteFinanciamientoDAO;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CofiaFuenteFinanciamientoDAOImpl extends HibernateDAOSuportUtil implements CofiaFuenteFinanciamientoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		getHibernateTemplate().saveOrUpdate(cofiaFuenteFinanciamiento);
	}

	@Transactional
	public void delete(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		getHibernateTemplate().delete(cofiaFuenteFinanciamiento);
	}

	@Transactional(readOnly = true)
	public CofiaFuenteFinanciamiento getById(Long idCofiaFuenteFinanciamiento) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CofiaFuenteFinanciamiento.class);

		criteria.add(Restrictions.eq("idCofiaFuenteFinanciamiento", idCofiaFuenteFinanciamiento));

		List<CofiaFuenteFinanciamiento> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (CofiaFuenteFinanciamiento) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CofiaFuenteFinanciamiento> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CofiaFuenteFinanciamiento.class);

		List<CofiaFuenteFinanciamiento> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
