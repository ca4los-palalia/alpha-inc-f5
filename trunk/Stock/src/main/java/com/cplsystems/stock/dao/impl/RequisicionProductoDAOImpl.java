/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.RequisicionProductoDAO;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.ProveedorService;




@Repository
public class RequisicionProductoDAOImpl extends HibernateDAOSuportUtil implements RequisicionProductoDAO{

	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private  EstatusRequisicionService estatusRequisicionService;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
	@Transactional
	public void save(RequisicionProducto requisicionProducto) {
		getHibernateTemplate().saveOrUpdate(requisicionProducto);
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
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByProducto(Producto producto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("producto", producto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByRequisicion(Requisicion requisicion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("requisicion", requisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByProveedor(Proveedor proveedor) {
		//EstatusRequisicion estado = estatusRequisicionService.getByClave("RQ");
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		//criteria.createAlias("rp.requisicion", "rq");
		
		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		//---------------------
		//criteria.add(Restrictions.eq("rq.estatusRequisicion", estado));
		//---------------------
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByLugar(Lugar lugar) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("lugar", lugar));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getAllRequisiciones() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		//criteria.setProjection(Projections.countDistinct("requisicion"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getRequisicionesConEstadoEspecifico(
			EstatusRequisicion estatusRequisicion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class, "rp");
		criteria.createAlias("rp.requisicion", "rq");
		criteria.add(Restrictions.eq("rq.estatusRequisicion", estatusRequisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getAllDistinctByProveedor() {
		EstatusRequisicion estado = estatusRequisicionService.getByClave("RQ");
		
		List<Proveedor> lista = new ArrayList<Proveedor>();
		//List<RequisicionProducto> listaObjeto = new ArrayList<RequisicionProducto>();
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class, "rp");
		criteria.createAlias("rp.requisicion", "rq");
		criteria.setProjection(Projections.distinct(Projections.property("proveedor")));
		criteria.add(Restrictions.eq("rq.estatusRequisicion", estado));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		//listaObjeto = criteria.list();
		lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByConfiaPartidaGenerica(
			CofiaPartidaGenerica cofiaPartidaGenerica) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("cofiaPartidaGenerica", cofiaPartidaGenerica));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByCotizacion(Cotizacion cotizacion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(RequisicionProducto.class);
		criteria.add(Restrictions.eq("cotizacion", cotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
}
