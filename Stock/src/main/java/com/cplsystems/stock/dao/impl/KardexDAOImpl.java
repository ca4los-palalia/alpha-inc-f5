package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.KardexDAO;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Organizacion;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class KardexDAOImpl extends HibernateDAOSuportUtil implements KardexDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Kardex kardex) {
		getHibernateTemplate().saveOrUpdate(kardex);
	}

	@Transactional
	public void delete(Kardex kardex) {
		getHibernateTemplate().delete(kardex);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Kardex getById(Long idKardex) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Kardex.class);

		criteria.add(Restrictions.eq("idKardex", idKardex));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Kardex) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getByEstatus(EstatusRequisicion estatus) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Kardex.class);

		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getByKardexProveedorEstatus(KardexProveedor kardexProveedor, EstatusRequisicion estatus) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.eq("kardexProveedor", kardexProveedor));
		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	

}
