package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.BancoDAO;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BancoDAOImpl extends HibernateDAOSuportUtil implements BancoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Banco banco) {
		getHibernateTemplate().saveOrUpdate(banco);
	}

	@Transactional
	public void delete(Banco banco) {
		getHibernateTemplate().delete(banco);
	}

	@Transactional(readOnly = true)
	public Banco getById(Long idBanco) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Banco.class);

		criteria.add(Restrictions.eq("idBanco", idBanco));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Banco> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Banco) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Banco> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Banco.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Banco> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
