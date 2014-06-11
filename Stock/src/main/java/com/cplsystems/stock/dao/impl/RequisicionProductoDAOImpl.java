/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionProductoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.RequisicionProducto;


/**
 * @author Carlos Palalía López
 */

@Repository
public class RequisicionProductoDAOImpl extends HibernateDAOSuportUtil implements RequisicionProductoDAO{

	@Transactional
	public void save(RequisicionProducto requisicionProducto) {
		getHibernateTemplate().save(requisicionProducto);
	}

	@Transactional
	public void update(RequisicionProducto requisicionProducto) {
		getHibernateTemplate().update(requisicionProducto);
	}

	@Transactional
	public void delete(RequisicionProducto requisicionProducto) {
		getHibernateTemplate().delete(requisicionProducto);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public RequisicionProducto getById(Long idRequisicionProducto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("idRequisionProducto", idRequisicionProducto));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByProducto(Contacto contacto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByRequisicion(Contacto contacto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByLugar(Lugar lugar) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getAllRequisiciones() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.setProjection(Projections.countDistinct("requisicion"));
		
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
}
