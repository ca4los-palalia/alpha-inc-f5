package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.AreaDAO;
import com.cplsystems.stock.domain.Area;
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
public class AreaDAOImpl extends HibernateDAOSuportUtil implements AreaDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Area area) {
		getHibernateTemplate().saveOrUpdate(area);
	}

	@Transactional
	public void update(Area area) {
		getHibernateTemplate().update(area);
	}

	@Transactional
	public void delete(Area posicion) {
		getHibernateTemplate().delete(posicion);
	}

	@Transactional(readOnly = true)
	public Area getById(Long idArea) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Area.class);

		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Area) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Area> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Area.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Area getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Area.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Area) lista.get(0) : null;
	}
}
