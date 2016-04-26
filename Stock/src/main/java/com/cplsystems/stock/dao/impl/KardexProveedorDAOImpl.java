package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.KardexProveedorDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Proveedor;

@Repository
public class KardexProveedorDAOImpl extends HibernateDAOSuportUtil implements KardexProveedorDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(KardexProveedor kardexProveedor) {
		getHibernateTemplate().saveOrUpdate(kardexProveedor);
	}

	@Transactional
	public void delete(KardexProveedor kardexProveedor) {
		getHibernateTemplate().delete(kardexProveedor);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public KardexProveedor getById(Long idKardexProveedor) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);

		criteria.add(Restrictions.eq("idKardexProveedor", idKardexProveedor));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (KardexProveedor) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<KardexProveedor> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<KardexProveedor> getByEstatus(EstatusRequisicion estatus) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);

		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<KardexProveedor> getByProveedorEstatus(Proveedor proveedor, EstatusRequisicion estatus) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);
		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
