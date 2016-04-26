package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.CalculosCostoDAO;
import com.cplsystems.stock.domain.CalculosCosto;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CalculosCostoDAOImpl extends HibernateDAOSuportUtil implements CalculosCostoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(CalculosCosto calculosCosto) {
		getHibernateTemplate().saveOrUpdate(calculosCosto);
	}

	@Transactional
	public void delete(CalculosCosto calculosCosto) {
		getHibernateTemplate().delete(calculosCosto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CalculosCosto getById(Long idCalculosCosto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CalculosCosto.class);

		criteria.add(Restrictions.eq("idCalculosCosto", idCalculosCosto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<CalculosCosto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (CalculosCosto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<CalculosCosto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CalculosCosto.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CalculosCosto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
