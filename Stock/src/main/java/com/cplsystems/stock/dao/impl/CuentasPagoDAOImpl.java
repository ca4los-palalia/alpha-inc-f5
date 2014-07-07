/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CuentasPagoDAO;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Proveedor;


/**
 * @author Carlos Palalía López
 */

@Repository
public class CuentasPagoDAOImpl extends HibernateDAOSuportUtil implements CuentasPagoDAO{

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

	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public CuentaPago getById(Long idCuentaPago) {
		List<CuentaPago> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CuentaPago.class);
		criteria.add(Restrictions.eq("idCuentaPago", idCuentaPago));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public List<CuentaPago> getAll() {
		List<CuentaPago> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CuentaPago.class);
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public List<CuentaPago> getByProveedor(Proveedor proveedor) {
		List<CuentaPago> lista = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CuentaPago.class);
		criteria.add(Restrictions.eq("proveedor", proveedor));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

}
