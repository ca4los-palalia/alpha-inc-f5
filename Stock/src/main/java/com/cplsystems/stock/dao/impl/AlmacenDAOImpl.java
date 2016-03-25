package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.AlmacenDAO;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AlmacenDAOImpl extends HibernateDAOSuportUtil implements AlmacenDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Almacen almacen) {
		getHibernateTemplate().saveOrUpdate(almacen);
	}

	@Transactional
	public void delete(Almacen almacen) {
		getHibernateTemplate().delete(almacen);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Almacen getById(Long idAlmacen) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Almacen.class);

		criteria.add(Restrictions.eq("idAlmacen", idAlmacen));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Almacen> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Almacen) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Almacen> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Almacen.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Almacen> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Almacen> getByArea(Area area) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Almacen.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("area", area));
		List<Almacen> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	/*
	

	
	*/
	
	
	
}
