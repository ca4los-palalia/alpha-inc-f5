package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.AlmacenEntradaDAO;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;

@Repository
public class AlmacenEntradaDAOImpl extends HibernateDAOSuportUtil implements AlmacenEntradaDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(AlmacenEntrada almacen) {
		getHibernateTemplate().saveOrUpdate(almacen);
	}

	@Transactional
	public void delete(AlmacenEntrada almacen) {
		getHibernateTemplate().delete(almacen);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public AlmacenEntrada getById(Long idAlmacen) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);

		criteria.add(Restrictions.eq("idAlmacenEntrada", idAlmacen));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByArea(Area area) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("area", area));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByCotizacion(Cotizacion cotizacion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("cotizacion", cotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByOrdenCompra(OrdenCompra ordenCompra) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("ordenCompra", ordenCompra));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByAlmacen(Almacen almacen) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("almacen", almacen));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByOrdenCompraProductoProveedor(OrdenCompra ordenCompra, Producto producto,
			Proveedor proveedor) {
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		if(ordenCompra != null)
			criteria.add(Restrictions.eq("ordenCompra", ordenCompra));
		if(producto != null)
			criteria.add(Restrictions.eq("producto", producto));
		if(proveedor != null)
			criteria.add(Restrictions.eq("proveedor", proveedor));
		
		
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
	}
}
