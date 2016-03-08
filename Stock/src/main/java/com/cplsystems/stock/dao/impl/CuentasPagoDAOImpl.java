package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CuentasPagoDAO;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Proveedor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CuentasPagoDAOImpl extends HibernateDAOSuportUtil implements CuentasPagoDAO {
	@Transactional
	public void save(CuentaPago cuentaPago) {
		getHibernateTemplate().saveOrUpdate(cuentaPago);
	}

	@Transactional
	public void update(CuentaPago cuentaPago) {
		getHibernateTemplate().saveOrUpdate(cuentaPago);
	}

	@Transactional
	public void delete(CuentaPago cuentaPago) {
		getHibernateTemplate().delete(cuentaPago);
	}

	@Transactional(readOnly = true)
	public CuentaPago getById(Long idCuentaPago) {
		List<CuentaPago> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CuentaPago.class);

		criteria.add(Restrictions.eq("idCuentaPago", idCuentaPago));
		lista = criteria.list();
		return lista.size() > 0 ? (CuentaPago) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CuentaPago> getAll() {
		List<CuentaPago> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CuentaPago.class);

		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<CuentaPago> getByProveedor(Proveedor proveedor) {
		List<CuentaPago> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(CuentaPago.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
}
