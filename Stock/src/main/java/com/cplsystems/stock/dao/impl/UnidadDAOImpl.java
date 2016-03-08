package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.UnidadDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
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
public class UnidadDAOImpl extends HibernateDAOSuportUtil implements UnidadDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Unidad unidad) {
		getHibernateTemplate().saveOrUpdate(unidad);
	}

	@Transactional
	public void update(Unidad unidad) {
		getHibernateTemplate().update(unidad);
	}

	@Transactional
	public void delete(Unidad unidad) {
		getHibernateTemplate().delete(unidad);
	}

	@Transactional(readOnly = true)
	public Unidad getById(Long idUnidad) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("idUnidad", idUnidad));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Unidad) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Unidad> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Unidad getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Unidad) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Unidad> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Unidad> getByUsuario(Usuarios usuario) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("usuario", usuario));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
